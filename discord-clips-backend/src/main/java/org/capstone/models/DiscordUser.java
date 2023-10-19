package org.capstone.models;

import java.util.Objects;

public class DiscordUser {
    private long discordUserId;
    private String username;

    public long getDiscordUserId() {
        return discordUserId;
    }

    public void setDiscordUserId(long discordUserId) {
        this.discordUserId = discordUserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscordUser that = (DiscordUser) o;
        return discordUserId == that.discordUserId && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discordUserId, username);
    }
}
