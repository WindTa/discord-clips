package org.capstone.data.jdbc;

import org.capstone.data.interfaces.ClipRepository;
import org.capstone.data.mappers.ClipMapper;
import org.capstone.data.mappers.ClipPlaylistMapper;
import org.capstone.models.Clip;
import org.capstone.models.ClipPlaylist;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Repository
public class ClipJdbcTemplateRepository implements ClipRepository {

    private final JdbcTemplate jdbcTemplate;

    public ClipJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Clip> findByDiscordUserId(long discordUserId) {
        final String sql =
               """
               select *
               from clip
               where discord_user_id = ?;
               """;

        return jdbcTemplate.query(sql, new ClipMapper(), discordUserId);
    }

    @Override
    public Clip findById(int clipId) {
        final String sql =
                """
                select *
                from clip
                where clip_id = ?;        
                """;

        Clip clip = jdbcTemplate.query(sql, new ClipMapper(), clipId)
                .stream().findFirst().orElse(null);

        if (clip != null) {
            addPlaylists(clip);
        }

        return clip;
    }

    @Override
    public Clip add(Clip clip) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("clip")
                .usingGeneratedKeyColumns("clip_id");

        HashMap<String, Object> args = new HashMap<>();
        args.put("clip_name", clip.getClipName());
        args.put("youtube_id", clip.getYoutubeId());
        args.put("start_time", clip.getStartTime());
        args.put("duration", clip.getDuration());
        args.put("volume", clip.getVolume());
        args.put("playback_speed", clip.getPlaybackSpeed());
        args.put("discord_user_id", clip.getDiscordUserId());

        int clipId = insert.executeAndReturnKey(args).intValue();
        clip.setClipId(clipId);

        return clip;
    }

    @Override
    public boolean update(Clip clip) {
        final String sql =
                """
                    update clip set
                        clip_name = ?,
                        youtube_id = ?,
                        start_time = ?,
                        duration = ?,
                        volume = ?,
                        playback_speed = ?
                    where clip_id = ?
                        and discord_user_id = ?;
                """;

        boolean result = jdbcTemplate.update(sql,
                clip.getClipName(), clip.getYoutubeId(),
                clip.getStartTime(), clip.getDuration(),
                clip.getVolume(), clip.getPlaybackSpeed(),
                clip.getClipId(), clip.getDiscordUserId()) > 0;

        return result;
    }

    @Override
    @Transactional
    public boolean deleteById(int clipId) {
        jdbcTemplate.update("delete from playlist_clip where clip_id = ?;", clipId);
        jdbcTemplate.update("delete from discord_server_clip where clip_id = ?;", clipId);
        return jdbcTemplate.update("delete from clip where clip_id = ?;", clipId) > 0;
    }

    private void addPlaylists(Clip clip) {
        final String sql =
                """
                select
                    pc.clip_id, pc.display_order,
                    p.playlist_id, p.playlist_name, p.discord_user_id
                from playlist_clip pc
                inner join playlist p on pc.playlist_id = p.playlist_id
                inner join clip c on pc.clip_id = c.clip_id
                where pc.clip_id = ?;
                """;

        List<ClipPlaylist> clipPlaylists = jdbcTemplate.query(sql, new ClipPlaylistMapper(), clip.getClipId());
        clip.setPlaylists(clipPlaylists);
    }
}