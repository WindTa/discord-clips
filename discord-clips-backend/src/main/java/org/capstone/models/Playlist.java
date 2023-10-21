package org.capstone.models;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private int playlistId;
    private String playlistName;
    private long discordUserId;

    private List<PlaylistClip> clips = new ArrayList<>();

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public long getDiscordUserId() {
        return discordUserId;
    }

    public void setDiscordUserId(long discordUserId) {
        this.discordUserId = discordUserId;
    }

    public List<PlaylistClip> getClips() {
        return clips;
    }

    public void setClips(List<PlaylistClip> clips) {
        this.clips = clips;
    }
}
