package org.capstone.data.jdbc;

import org.capstone.data.interfaces.ClipPlaylistRepository;
import org.capstone.models.ClipPlaylist;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClipPlaylistJdbcTemplateRepository implements ClipPlaylistRepository {
    private final JdbcTemplate jdbcTemplate;

    public ClipPlaylistJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean add(ClipPlaylist clipPlaylist) {
        final String sql =
                """
                    insert into playlist_clip
                        (playlist_id, clip_id, display_order)
                    values
                        (?, ?, ?);
                """;

        return jdbcTemplate.update(sql,
                clipPlaylist.getPlaylist().getPlaylistId(),
                clipPlaylist.getClipId(),
                clipPlaylist.getDisplayOrder()) > 0;
    }

    @Override
    public boolean deleteByKey(int clipId, int playlistId) {
        final String sql =
                """
                    delete from playlist_clip
                    where playlist_id = ?
                        and clip_id = ?
                """;

        return jdbcTemplate.update(sql, playlistId, clipId) > 0;
    }
}
