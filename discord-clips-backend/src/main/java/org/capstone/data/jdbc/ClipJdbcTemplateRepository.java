package org.capstone.data.jdbc;

import org.capstone.data.interfaces.ClipRepository;
import org.capstone.data.mappers.ClipMapper;
import org.capstone.models.Clip;
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

        return jdbcTemplate.query(sql, new ClipMapper(), clipId)
                .stream().findFirst().orElse(null);
    }

    private void addPlaylists(Clip clip) {

    }
}