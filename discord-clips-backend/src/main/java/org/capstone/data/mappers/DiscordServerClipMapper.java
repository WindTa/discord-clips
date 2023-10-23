package org.capstone.data.mappers;

import org.capstone.models.DiscordServerClip;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscordServerClipMapper implements RowMapper<DiscordServerClip> {
    @Override
    public DiscordServerClip mapRow(ResultSet rs, int i) throws SQLException {
        ClipMapper clipMapper = new ClipMapper();
        DiscordServerClip discordServerClip = new DiscordServerClip(
                rs.getLong("discord_server_id"),
                clipMapper.mapRow(rs, i)
        );

        return discordServerClip;
    }
}
