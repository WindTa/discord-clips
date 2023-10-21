package org.capstone.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clip {
    private int clipId;
    private String clipName;
    private String youtubeId;
    private double startTime;
    private double endTime;
    private double volume;
    private double playbackSpeed;
    private long discordUserId;

    private List<ClipPlaylist> playlists = new ArrayList<>();

    public int getClipId() {
        return clipId;
    }

    public void setClipId(int clipId) {
        this.clipId = clipId;
    }

    public String getClipName() {
        return clipName;
    }

    public void setClipName(String clipName) {
        this.clipName = clipName;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getPlaybackSpeed() {
        return playbackSpeed;
    }

    public void setPlaybackSpeed(double playbackSpeed) {
        this.playbackSpeed = playbackSpeed;
    }

    public long getDiscordUserId() {
        return discordUserId;
    }

    public void setDiscordUserId(long discordUserId) {
        this.discordUserId = discordUserId;
    }

    public List<ClipPlaylist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<ClipPlaylist> playlists) {
        this.playlists = playlists;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clip clip = (Clip) o;
        return clipId == clip.clipId && discordUserId == clip.discordUserId && Objects.equals(clipName, clip.clipName) && Objects.equals(youtubeId, clip.youtubeId) && Objects.equals(startTime, clip.startTime) && Objects.equals(endTime, clip.endTime) && Objects.equals(volume, clip.volume) && Objects.equals(playbackSpeed, clip.playbackSpeed) && Objects.equals(playlists, clip.playlists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clipId, clipName, youtubeId, startTime, endTime, volume, playbackSpeed, discordUserId, playlists);
    }
}
