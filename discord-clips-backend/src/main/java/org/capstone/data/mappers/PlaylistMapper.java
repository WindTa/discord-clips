package org.capstone.data.mappers;

import org.capstone.models.Playlist;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistMapper implements RowMapper<Playlist> {

    @Override
    public Playlist mapRow(ResultSet rs, int i) throws SQLException {
        Playlist playlist = new Playlist();
        playlist.setPlaylistId(rs.getInt("playlist_id"));
        playlist.setPlaylistName(rs.getString("playlist_name"));
        playlist.setDiscordUserId(rs.getLong("discord_user_id"));

        return playlist;
    }
}
