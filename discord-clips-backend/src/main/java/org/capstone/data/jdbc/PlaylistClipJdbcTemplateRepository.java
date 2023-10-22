package org.capstone.data.jdbc;

import org.capstone.data.interfaces.PlaylistClipRepository;
import org.capstone.models.PlaylistClip;
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
        return false;
    }

    @Override
    public boolean update(PlaylistClip playlistClip) {
        return false;
    }

    @Override
    public boolean delete(int playlistId, int clipId) {
        return false;
    }
}
