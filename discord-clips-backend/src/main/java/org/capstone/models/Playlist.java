package org.capstone.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Playlist {
    private int playlistId;
    private String playlistName;
    private long discordUserId;

    private List<PlaylistClip> clips = new ArrayList<>();

    public Playlist(int playlistId, String playlistName, long discordUserId) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.discordUserId = discordUserId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist = (Playlist) o;
        return playlistId == playlist.playlistId && discordUserId == playlist.discordUserId && Objects.equals(playlistName, playlist.playlistName) && Objects.equals(clips, playlist.clips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistId, playlistName, discordUserId, clips);
    }
}
