package org.capstone.data.jdbc;

import org.capstone.data.interfaces.PlaylistClipRepository;
import org.capstone.models.PlaylistClip;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PlaylistClipJdbcTemplateRepository implements PlaylistClipRepository {

    private final JdbcTemplate jdbcTemplate;

    public PlaylistClipJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean add(PlaylistClip playlistClip) {
        final String sql =
                """
                    insert into playlist_clip 
                        (playlist_id, clip_id, display_order)
                    values
                        (?, ?, ?);
                """;

        return jdbcTemplate.update(sql,
                playlistClip.getPlaylistId(),
                playlistClip.getClip().getClipId(),
                playlistClip.getDisplayOrder()) > 0;
    }

    @Override
    public boolean deleteByKey(int playlistId, int clipId) {
        final String sql =
                """
                    delete from playlist_clip
                    where playlist_id = ?
                        and clip_id = ?
                """;

        return jdbcTemplate.update(sql, playlistId, clipId) > 0;
    }
}
