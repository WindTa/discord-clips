package org.capstone.data.jdbc;

import org.capstone.data.interfaces.PlaylistRepository;
import org.capstone.models.Playlist;

import java.util.List;

public class PlaylistJdbcTemplateRepository implements PlaylistRepository {
    @Override
    public List<Playlist> findByDiscordUserId(long discordUserId) {
        return null;
    }

    @Override
    public Playlist findById(int playlistId) {
        return null;
    }

    private void addClips(Playlist playlist) {

    }
}
