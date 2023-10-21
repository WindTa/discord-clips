package org.capstone.data.jdbc;

import org.capstone.data.interfaces.ClipRepository;
import org.capstone.models.Clip;
import org.capstone.models.Playlist;

import java.util.List;

public class ClipJdbcTemplateRepository implements ClipRepository {
    @Override
    public List<Clip> findByDiscordUserId(long discordUserId) {
        return null;
    }

    @Override
    public Clip findById(int clipId) {
        return null;
    }

    private void addPlaylists(Clip clip) {

    }
}
