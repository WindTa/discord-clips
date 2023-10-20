package org.capstone.data.jdbc;

import org.capstone.data.interfaces.DiscordServerRepository;
import org.capstone.data.mappers.DiscordServerMapper;
import org.capstone.models.DiscordServer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class DiscordServerJdbcTemplateRepository implements DiscordServerRepository {

    private final JdbcTemplate jdbcTemplate;

    public DiscordServerJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public DiscordServer findById(long discordServerId) {
        final String sql = """
                select * 
                from discord_server
                where discord_server_id = ?;
                """;

        DiscordServer discordServer = jdbcTemplate.query(sql, new DiscordServerMapper(), discordServerId)
                .stream().findFirst().orElse(null);

        return discordServer;
    }

    @Override
    public DiscordServer add(DiscordServer discordServer) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("discord_server");

        HashMap<String, Object> args = new HashMap<>();
        args.put("discord_server_id", discordServer.getDiscordServerId());
        args.put("servername", discordServer.getServername());

        long discordServerId = insert.execute(args);

        return discordServer;
    }

    @Override
    public boolean update(DiscordServer discordServer) {
        final String sql =
                """
                update discord_server set
                    servername = ?
                    where discord_server_id = ?
                """;

        boolean result = jdbcTemplate.update(sql,
                discordServer.getServername(),
                discordServer.getDiscordServerId()) > 0;

        return result;
    }

    @Override
    public boolean deleteById(long discordServerId) {
        return jdbcTemplate.update("delete from discord_server where discord_server_id = ?;", discordServerId) > 0;
    }
}
