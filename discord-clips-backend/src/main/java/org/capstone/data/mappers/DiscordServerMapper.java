package org.capstone.data.mappers;

import org.capstone.models.DiscordServer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscordServerMapper implements RowMapper<DiscordServer> {
    @Override
    public DiscordServer mapRow(ResultSet resultSet, int i) throws SQLException {
        DiscordServer discordServer = new DiscordServer();
        discordServer.setDiscordServerId(resultSet.getLong("discord_server_id"));
        discordServer.setServername(resultSet.getString("servername"));

        return discordServer;
    }
}
