package org.capstone.data;

import org.capstone.models.DiscordUser;
import org.springframework.transaction.annotation.Transactional;

public interface DiscordUserRepository {
    DiscordUser findById(long discordUserId);
    DiscordUser add(DiscordUser discordUser);
    boolean update(DiscordUser discordUser);
    boolean deleteById(long discordUserId);
}
