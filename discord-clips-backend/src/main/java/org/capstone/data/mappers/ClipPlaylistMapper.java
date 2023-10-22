package org.capstone.data.mappers;

import org.capstone.models.ClipPlaylist;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClipPlaylistMapper implements RowMapper<ClipPlaylist> {
    @Override
    public ClipPlaylist mapRow(ResultSet rs, int i) throws SQLException {
        PlaylistMapper playlistMapper = new PlaylistMapper();

        ClipPlaylist clipPlaylist = new ClipPlaylist(
                rs.getInt("clip_id"),
                playlistMapper.mapRow(rs, i),
                rs.getInt("display_order")
        );

        return clipPlaylist;
    }
}
