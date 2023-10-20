package org.capstone.data.interfaces;

import org.capstone.models.DiscordServer;
import org.capstone.models.DiscordUser;

public interface DiscordServerRepository {
    DiscordServer findById(long discordServerId);
    DiscordServer add(DiscordServer discordServer);
    boolean update(DiscordServer discordServer);
    boolean deleteById(long discordServerId);
}
