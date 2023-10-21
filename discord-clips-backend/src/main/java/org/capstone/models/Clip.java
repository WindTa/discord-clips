package org.capstone.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Clip {
    private int clipId;
    private String clipName;
    private String youtubeId;
    private BigDecimal startTime;
    private BigDecimal endTime;
    private BigDecimal volume;
    private BigDecimal playbackSpeed;
    private long discordUserId;

    private List<Playlist> playlists = new ArrayList<>();

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

    public BigDecimal getStartTime() {
        return startTime;
    }

    public void setStartTime(BigDecimal startTime) {
        this.startTime = startTime;
    }

    public BigDecimal getEndTime() {
        return endTime;
    }

    public void setEndTime(BigDecimal endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getPlaybackSpeed() {
        return playbackSpeed;
    }

    public void setPlaybackSpeed(BigDecimal playbackSpeed) {
        this.playbackSpeed = playbackSpeed;
    }

    public long getDiscordUserId() {
        return discordUserId;
    }

    public void setDiscordUserId(long discordUserId) {
        this.discordUserId = discordUserId;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }
}
