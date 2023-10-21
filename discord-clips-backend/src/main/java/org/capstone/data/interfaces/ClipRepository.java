package org.capstone.data.interfaces;

import org.capstone.models.Clip;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClipRepository {
    List<Clip> findByDiscordUserId(long discordUserId);

    @Transactional
    Clip findById(int clipId);
}
