package org.capstone.data.mappers;

import org.capstone.models.Clip;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClipMapper implements RowMapper<Clip> {
    @Override
    public Clip mapRow(ResultSet rs, int i) throws SQLException {
        Clip clip = new Clip();
        clip.setClipId(rs.getInt("clip_id"));
        clip.setClipName(rs.getString("clip_name"));
        clip.setYoutubeId(rs.getString("youtube_id"));
        clip.setStartTime(rs.getDouble("start_time"));
        clip.setEndTime(rs.getDouble("end_time"));
        clip.setVolume(rs.getDouble("volume"));
        clip.setPlaybackSpeed(rs.getDouble("playback_speed"));
        clip.setDiscordUserId(rs.getLong("discord_user_id"));

        return clip;
    }
}
