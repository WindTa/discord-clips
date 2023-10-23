package org.capstone.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DiscordServer {
    private long discordServerId;
    private String servername;

    private List<DiscordServerClip> clips = new ArrayList<>();

    public DiscordServer() {}

    public DiscordServer(long discordServerId, String servername) {
        this.discordServerId = discordServerId;
        this.servername = servername;
    }

    public long getDiscordServerId() {
        return discordServerId;
    }

    public void setDiscordServerId(long discordServerId) {
        this.discordServerId = discordServerId;
    }

    public String getServername() {
        return servername;
    }

    public void setServername(String username) {
        this.servername = username;
    }

    public List<DiscordServerClip> getClips() {
        return clips;
    }

    public void setClips(List<DiscordServerClip> clips) {
        this.clips = clips;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscordServer that = (DiscordServer) o;
        return discordServerId == that.discordServerId && Objects.equals(servername, that.servername) && Objects.equals(clips, that.clips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discordServerId, servername, clips);
    }
}
