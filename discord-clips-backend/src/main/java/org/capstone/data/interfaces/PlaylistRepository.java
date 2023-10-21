package org.capstone.data.interfaces;

import org.capstone.models.Playlist;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlaylistRepository {
    List<Playlist> findByDiscordUserId(long discordUserId);

    @Transactional
    Playlist findById(int playlistId);
}
