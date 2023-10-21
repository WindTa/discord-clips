package org.capstone.data.jdbc;

import org.capstone.data.interfaces.ClipRepository;
import org.capstone.data.mappers.ClipMapper;
import org.capstone.data.mappers.ClipPlaylistMapper;
import org.capstone.models.Clip;
import org.capstone.models.ClipPlaylist;
import org.capstone.models.Playlist;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
        return null;
    }

    @Override
    public boolean update(Clip clip) {
        return false;
    }

    @Override
    public boolean deleteById(int clipId) {
        return false;
    }

    private void addPlaylists(Clip clip) {
        final String sql =
                """
                select
                    pc.display_order,
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