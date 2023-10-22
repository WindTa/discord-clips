package org.capstone.models;

import java.util.Objects;

public class DiscordServer {
    private long discordServerId;
    private String username;

    public DiscordServer() {}

    public DiscordServer(long discordServerId, String username) {
        this.discordServerId = discordServerId;
        this.username = username;
    }

    public long getDiscordServerId() {
        return discordServerId;
    }

    public void setDiscordServerId(long discordServerId) {
        this.discordServerId = discordServerId;
    }

    public String getServername() {
        return username;
    }

    public void setServername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscordServer that = (DiscordServer) o;
        return discordServerId == that.discordServerId && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discordServerId, username);
    }
}
