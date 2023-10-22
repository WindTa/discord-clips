package org.capstone.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clip {
    private int clipId;
    private String clipName;
    private String youtubeId;
    private double startTime;
    private double duration;
    private double volume;
    private double playbackSpeed;
    private long discordUserId;

    private List<ClipPlaylist> playlists = new ArrayList<>();

    public Clip(int clipId, String clipName, String youtubeId, double startTime, double duration, double volume, double playbackSpeed, long discordUserId) {
        this.clipId = clipId;
        this.clipName = clipName;
        this.youtubeId = youtubeId;
        this.startTime = startTime;
        this.duration = duration;
        this.volume = volume;
        this.playbackSpeed = playbackSpeed;
        this.discordUserId = discordUserId;
    }

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

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
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
        return clipId == clip.clipId && discordUserId == clip.discordUserId && Objects.equals(clipName, clip.clipName) && Objects.equals(youtubeId, clip.youtubeId) && Objects.equals(startTime, clip.startTime) && Objects.equals(duration, clip.duration) && Objects.equals(volume, clip.volume) && Objects.equals(playbackSpeed, clip.playbackSpeed) && Objects.equals(playlists, clip.playlists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clipId, clipName, youtubeId, startTime, duration, volume, playbackSpeed, discordUserId, playlists);
    }
}
