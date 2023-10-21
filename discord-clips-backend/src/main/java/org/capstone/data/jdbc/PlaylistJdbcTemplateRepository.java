package org.capstone.data.jdbc;

import org.capstone.data.interfaces.PlaylistRepository;
import org.capstone.data.mappers.PlaylistClipMapper;
import org.capstone.data.mappers.PlaylistMapper;
import org.capstone.models.Playlist;
import org.capstone.models.PlaylistClip;
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

        Playlist playlist = jdbcTemplate.query(sql, new PlaylistMapper(), playlistId)
                .stream().findFirst().orElse(null);

        if (playlist != null) {
            addClips(playlist);
        }

        return playlist;
    }

    private void addClips(Playlist playlist) {
        final String sql =
                """
                select
                    pc.display_order,
                    c.clip_id, c.clip_name, c.youtube_id,
                    c.start_time, c.end_time,
                    c.volume, c.playback_speed,
                    c.discord_user_id
                from playlist_clip pc
                inner join playlist p on pc.playlist_id = p.playlist_id
                inner join clip c on pc.clip_id = c.clip_id
                where pc.playlist_id = ?;
                """;

        List<PlaylistClip> playlistClips = jdbcTemplate.query(sql, new PlaylistClipMapper(), playlist.getPlaylistId());
        playlist.setClips(playlistClips);
    }
}
