package org.capstone.models;

import java.util.Objects;

public class DiscordServerClip {
    private long discordServerId;
    private Clip clip;

    public DiscordServerClip(long discordServerId, Clip clip) {
        this.discordServerId = discordServerId;
        this.clip = clip;
    }

    public long getDiscordServerId() {
        return discordServerId;
    }

    public void setDiscordServerId(int discordServerId) {
        this.discordServerId = discordServerId;
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscordServerClip that = (DiscordServerClip) o;
        return discordServerId == that.discordServerId && Objects.equals(clip, that.clip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discordServerId, clip);
    }
}
