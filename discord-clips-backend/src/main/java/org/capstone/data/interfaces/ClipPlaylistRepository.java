package org.capstone.data.interfaces;

import org.capstone.models.ClipPlaylist;
import org.capstone.models.PlaylistClip;

public interface ClipPlaylistRepository {
    boolean add(ClipPlaylist clipPlaylist);
    boolean deleteByKey(int clipId, int playlistId);
}