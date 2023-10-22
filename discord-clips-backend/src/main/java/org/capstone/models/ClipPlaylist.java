package org.capstone.models;

public class ClipPlaylist {
    private int clipId;
    private Playlist playlist;
    private int displayOrder;

    public ClipPlaylist(int clipId, Playlist playlist, int displayOrder) {
        this.clipId = clipId;
        this.playlist = playlist;
        this.displayOrder = displayOrder;
    }

    public int getClipId() {
        return clipId;
    }

    public void setClipId(int clipId) {
        this.clipId = clipId;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }
}
