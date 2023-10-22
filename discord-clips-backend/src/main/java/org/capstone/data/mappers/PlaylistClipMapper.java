package org.capstone.data.mappers;

import org.capstone.models.PlaylistClip;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistClipMapper implements RowMapper<PlaylistClip> {
    @Override
    public PlaylistClip mapRow(ResultSet rs, int i) throws SQLException {
        ClipMapper clipMapper = new ClipMapper();
        PlaylistClip playlistClip = new PlaylistClip(
                rs.getInt("playlist_id"),
                clipMapper.mapRow(rs, i),
                rs.getInt("display_order")
        );

        return playlistClip;
    }
}
