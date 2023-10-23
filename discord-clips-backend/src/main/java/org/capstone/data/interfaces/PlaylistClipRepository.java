package org.capstone.data.interfaces;

import org.capstone.models.PlaylistClip;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

public interface PlaylistClipRepository {
    boolean add(PlaylistClip playlistClip);
    boolean deleteByKey(int playlistId, int clipId);
}
