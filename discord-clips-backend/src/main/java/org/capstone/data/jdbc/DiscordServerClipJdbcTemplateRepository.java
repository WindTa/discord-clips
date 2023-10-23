package org.capstone.data.jdbc;

import org.capstone.data.interfaces.DiscordServerClipRepository;
import org.capstone.models.DiscordServerClip;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DiscordServerClipJdbcTemplateRepository implements DiscordServerClipRepository {
    private final JdbcTemplate jdbcTemplate;

    public DiscordServerClipJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean add(DiscordServerClip discordServerClip) {
        final String sql =
                """
                    insert into discord_server_clip
                        (discord_server_id, clip_id)
                    values
                        (?, ?);
                """;

        return jdbcTemplate.update(sql,
                discordServerClip.getDiscordServerId(),
                discordServerClip.getClip().getClipId()) > 0;
    }

    @Override
    public boolean deleteByKey(long discordServerId, int clipId) {
        final String sql =
                """
                    delete from discord_server_clip
                    where discord_server_id = ?
                        and clip_id = ?;        
                """;

        return jdbcTemplate.update(sql, discordServerId, clipId) > 0;
    }
}
