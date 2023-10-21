package org.capstone.data.mappers;

import org.capstone.models.ClipPlaylist;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClipPlaylistMapper implements RowMapper<ClipPlaylist> {
    @Override
    public ClipPlaylist mapRow(ResultSet rs, int i) throws SQLException {
        ClipPlaylist clipPlaylist = new ClipPlaylist();

        clipPlaylist.setDisplayOrder(rs.getInt("display_order"));

        PlaylistMapper playlistMapper = new PlaylistMapper();
        clipPlaylist.setPlaylist(playlistMapper.mapRow(rs, i));

        return clipPlaylist;
    }
}
