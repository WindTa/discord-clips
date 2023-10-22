package org.capstone.data.interfaces;

import org.capstone.models.PlaylistClip;

public interface PlaylistClipRepository {
    boolean add(PlaylistClip playlistClip);
    boolean update(PlaylistClip playlistClip);
    boolean delete(int playlistId, int clipId);
}
