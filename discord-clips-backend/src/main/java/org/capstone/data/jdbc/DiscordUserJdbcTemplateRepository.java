package org.capstone.data.jdbc;

import org.capstone.data.interfaces.DiscordUserRepository;
import org.capstone.data.mappers.DiscordUserMapper;
import org.capstone.models.DiscordUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class DiscordUserJdbcTemplateRepository implements DiscordUserRepository {

    private final JdbcTemplate jdbcTemplate;

    public DiscordUserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public DiscordUser findById(long discordUserId) {
        final String sql = """
                select * 
                from discord_user
                where discord_user_id = ?;
                """;

        DiscordUser discordUser = jdbcTemplate.query(sql, new DiscordUserMapper(), discordUserId)
                .stream().findFirst().orElse(null);

        return discordUser;
    }

    @Override
    public DiscordUser add(DiscordUser discordUser) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("discord_user");

        HashMap<String, Object> args = new HashMap<>();
        args.put("discord_user_id", discordUser.getDiscordUserId());
        args.put("username", discordUser.getUsername());

        long discordUserId = insert.execute(args);

        return discordUser;
    }

    @Override
    public boolean update(DiscordUser discordUser) {
        final String sql =
                """
                update discord_user set
                    username = ?
                    where discord_user_id = ?
                """;

        boolean result = jdbcTemplate.update(sql,
                discordUser.getUsername(),
                discordUser.getDiscordUserId()) > 0;

        return result;
    }

    @Override
    public boolean deleteById(long discordUserId) {
        return jdbcTemplate.update("delete from discord_user where discord_user_id = ?;", discordUserId) > 0;
    }
}
