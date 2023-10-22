package org.capstone.data.mappers;

import org.capstone.models.Clip;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClipMapper implements RowMapper<Clip> {
    @Override
    public Clip mapRow(ResultSet rs, int i) throws SQLException {
        Clip clip = new Clip(
                rs.getInt("clip_id"),
                rs.getString("clip_name"),
                rs.getString("youtube_id"),
                rs.getDouble("start_time"),
                rs.getDouble("duration"),
                rs.getDouble("volume"),
                rs.getDouble("playback_speed"),
                rs.getLong("discord_user_id")
        );

        return clip;
    }
}
