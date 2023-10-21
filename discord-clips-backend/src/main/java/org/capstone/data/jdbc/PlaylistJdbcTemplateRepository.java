package org.capstone.data.jdbc;

import org.capstone.data.interfaces.PlaylistRepository;
import org.capstone.data.mappers.PlaylistMapper;
import org.capstone.models.Playlist;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaylistJdbcTemplateRepository implements PlaylistRepository {

    private final JdbcTemplate jdbcTemplate;
    public PlaylistJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Playlist> findByDiscordUserId(long discordUserId) {
        final String sql =
                """
                select *
                from playlist
                where discord_user_id = ?;
                """;

        return jdbcTemplate.query(sql, new PlaylistMapper(), discordUserId);
    }

    @Override
    public Playlist findById(int playlistId) {
        final String sql =
                """
                select *
                from playlist
                where playlist_id = ?;
                """;

        return jdbcTemplate.query(sql, new PlaylistMapper(), playlistId)
                .stream().findFirst().orElse(null);
    }

    private void addClips(Playlist playlist) {

    }
}
