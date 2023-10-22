package org.capstone.models;

import java.util.Objects;

public class PlaylistClip {
    private int playlistId;
    private Clip clip;
    private int displayOrder;

    public PlaylistClip(int playlistId, Clip clip, int displayOrder) {
        this.playlistId = playlistId;
        this.clip = clip;
        this.displayOrder = displayOrder;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaylistClip that = (PlaylistClip) o;
        return playlistId == that.playlistId && displayOrder == that.displayOrder && Objects.equals(clip, that.clip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistId, clip, displayOrder);
    }
}
