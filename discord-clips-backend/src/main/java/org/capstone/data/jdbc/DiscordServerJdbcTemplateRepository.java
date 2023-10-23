package org.capstone.data.jdbc;

import org.capstone.data.interfaces.DiscordServerRepository;
import org.capstone.data.mappers.DiscordServerClipMapper;
import org.capstone.data.mappers.DiscordServerMapper;
import org.capstone.models.DiscordServer;
import org.capstone.models.DiscordServerClip;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

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

        if (discordServer != null) {
            addClips(discordServer);
        }

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
    @Transactional
    public boolean deleteById(long discordServerId) {
        jdbcTemplate.update("delete from discord_server_clip where discord_server_id = ?;", discordServerId);
        return jdbcTemplate.update("delete from discord_server where discord_server_id = ?;", discordServerId) > 0;
    }

    private void addClips(DiscordServer discordServer) {
        final String sql =
                """
                    select
                        dsc.discord_server_id,
                        c.clip_id, c.clip_name, c.youtube_id,
                        c.start_time, c.duration,
                        c.volume, c.playback_speed,
                        c.discord_user_id
                    from discord_server_clip dsc
                    inner join discord_server sc on dsc.discord_server_id = sc.discord_server_id
                    inner join clip c on dsc.clip_id = c.clip_id
                    where dsc.discord_server_id = ?;
                """;

        List<DiscordServerClip> discordServerClips =
                jdbcTemplate
                        .query(sql,
                                new DiscordServerClipMapper(), discordServer.getDiscordServerId());
        discordServer.setClips(discordServerClips);
    }
}
