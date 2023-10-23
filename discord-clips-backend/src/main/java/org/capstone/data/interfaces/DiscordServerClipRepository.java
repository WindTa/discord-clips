package org.capstone.data.interfaces;

import org.capstone.models.DiscordServerClip;

public interface DiscordServerClipRepository {
    boolean add(DiscordServerClip discordServerClip);
    boolean deleteByKey(long discordServerId, int clipId);
}
