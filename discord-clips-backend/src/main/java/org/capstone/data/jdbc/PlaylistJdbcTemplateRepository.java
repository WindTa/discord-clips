package org.capstone.data.jdbc;

import org.capstone.data.interfaces.PlaylistRepository;
import org.capstone.data.mappers.PlaylistClipMapper;
import org.capstone.data.mappers.PlaylistMapper;
import org.capstone.models.Playlist;
import org.capstone.models.PlaylistClip;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Repository
public class PlaylistJdbcTemplateRepository implements PlaylistRepository {

    private final JdbcTemplate jdbcTemplate;
    public PlaylistJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Playlist> findByDiscordUserId(long discordUserId) {
        final String sql =
                """
                select *
                from playlist
                where discord_user_id = ?;
                """;

        return jdbcTemplate.query(sql, new PlaylistMapper(), discordUserId);
    }

    @Override
    @Transactional
    public Playlist findById(int playlistId) {
        final String sql =
                """
                select *
                from playlist
                where playlist_id = ?;
                """;

        Playlist playlist = jdbcTemplate.query(sql, new PlaylistMapper(), playlistId)
                .stream().findFirst().orElse(null);

        if (playlist != null) {
            addClips(playlist);
        }

        return playlist;
    }

    @Override
    public Playlist add(Playlist playlist) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("playlist")
                .usingGeneratedKeyColumns("playlist_id");

        HashMap<String, Object> args = new HashMap<>();
        args.put("playlist_name", playlist.getPlaylistName());
        args.put("discord_user_id", playlist.getDiscordUserId());

        int playlistId = insert.executeAndReturnKey(args).intValue();
        playlist.setPlaylistId(playlistId);

        return playlist;
    }

    @Override
    public boolean update(Playlist playlist) {
        final String sql =
                """
                    update playlist set
                        playlist_name = ?
                    where playlist_id = ?
                        and discord_user_id = ?;
                """;

        boolean result = jdbcTemplate.update(sql,
                playlist.getPlaylistName(),
                playlist.getPlaylistId(),
                playlist.getDiscordUserId()) > 0;

        return result;
    }

    @Override
    @Transactional
    public boolean deleteById(int playlistId) {
        jdbcTemplate.update("delete from playlist_clip where playlist_id = ?;", playlistId);
        return jdbcTemplate.update("delete from playlist where playlist_id = ?;", playlistId) > 0;
    }

    private void addClips(Playlist playlist) {
        final String sql =
                """
                select
                    pc.display_order,
                    c.clip_id, c.clip_name, c.youtube_id,
                    c.start_time, c.end_time,
                    c.volume, c.playback_speed,
                    c.discord_user_id
                from playlist_clip pc
                inner join playlist p on pc.playlist_id = p.playlist_id
                inner join clip c on pc.clip_id = c.clip_id
                where pc.playlist_id = ?;
                """;

        List<PlaylistClip> playlistClips = jdbcTemplate.query(sql, new PlaylistClipMapper(), playlist.getPlaylistId());
        playlist.setClips(playlistClips);
    }
}
