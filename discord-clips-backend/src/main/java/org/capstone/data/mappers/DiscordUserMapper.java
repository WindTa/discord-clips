package org.capstone.data.mappers;

import org.capstone.models.DiscordUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscordUserMapper implements RowMapper<DiscordUser> {
    @Override
    public DiscordUser mapRow(ResultSet resultSet, int i) throws SQLException {
        DiscordUser discordUser = new DiscordUser(
                resultSet.getLong("discord_user_id"),
                resultSet.getString("username")
        );

        return discordUser;
    }
}
