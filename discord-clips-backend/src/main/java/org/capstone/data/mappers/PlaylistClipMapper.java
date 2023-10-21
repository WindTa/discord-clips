package org.capstone.data.mappers;

import org.capstone.models.PlaylistClip;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistClipMapper implements RowMapper<PlaylistClip> {
    @Override
    public PlaylistClip mapRow(ResultSet rs, int i) throws SQLException {
        PlaylistClip playlistClip = new PlaylistClip();

        playlistClip.setPlaylistId(rs.getInt("playlist_id"));
        playlistClip.setDisplayOrder(rs.getInt("display_order"));

        ClipMapper clipMapper = new ClipMapper();
        playlistClip.setClip(clipMapper.mapRow(rs, i));

        return playlistClip;
    }
}
