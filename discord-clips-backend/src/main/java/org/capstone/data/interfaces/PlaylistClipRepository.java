package org.capstone.data.interfaces;

import org.capstone.models.PlaylistClip;

public interface PlaylistClipRepository {
    boolean add(PlaylistClip playlistClip);
    boolean deleteByKey(int playlistId, int clipId);
}
