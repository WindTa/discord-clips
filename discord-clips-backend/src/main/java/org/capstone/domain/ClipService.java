package org.capstone.domain;

import org.capstone.data.interfaces.*;
import org.capstone.models.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClipService {

    private final DiscordUserRepository discordUserRepository;
    private final PlaylistRepository playlistRepository;
    private final ClipPlaylistRepository clipPlaylistRepository;
    private final ClipRepository clipRepository;

    public ClipService(DiscordUserRepository discordUserRepository, PlaylistRepository playlistRepository,
                       ClipPlaylistRepository clipPlaylistRepository, ClipRepository clipRepository) {
        this.discordUserRepository = discordUserRepository;
        this.playlistRepository = playlistRepository;
        this.clipPlaylistRepository = clipPlaylistRepository;
        this.clipRepository = clipRepository;
    }

    public List<Clip> findByDiscordUserId(long discordUserId) {
        return clipRepository.findByDiscordUserId(discordUserId);
    }

    public Clip findById(int clipId) {
        return clipRepository.findById(clipId);
    }

    public Result<Clip> add(Clip clip) {
        Result<Clip> result = validate(clip);
        if (!result.isSuccess()) {
            return result;
        }

        if (clip.getClipId() != 0) {
            result.addMessage("clipId must be set for `add` operation", ResultType.INVALID);
            return result;
        }

        clip = clipRepository.add(clip);
        result.setPayload(clip);
        return result;
    }

    public Result<Clip> update(Clip clip) {
        Result<Clip> result = validate(clip);
        if (!result.isSuccess()) {
            return result;
        }

        if (clip.getClipId() <= 0) {
            result.addMessage("clipId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!clipRepository.update(clip)) {
            String msg = String.format("clip does not exist", clip.getClipId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int clipId) {
        return clipRepository.deleteById(clipId);
    }

    private Result<Clip> validate(Clip clip) {
        Result<Clip> result = new Result<>();

        validateClip(clip, result);
        if (!result.isSuccess()) {
            return result;
        }

        if (discordUserRepository.findById(clip.getDiscordUserId()) == null) {
            result.addMessage("discord user does not exist", ResultType.NOT_FOUND);
        }

        return result;
    }

    private void validateClip(Clip clip, Result<Clip> result) {
        if (clip == null) {
            result.addMessage("clip cannot be null", ResultType.INVALID);
            return;
        }

        if (clip.getClipId() < 0) {
            result.addMessage("clipId must be >= 0", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(clip.getClipName())) {
            result.addMessage("clipName is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(clip.getYoutubeId())) {
            result.addMessage("youtubeId is required", ResultType.INVALID);
        }

        if (clip.getStartTime() < 0) {
            result.addMessage("startTime must be >= 0", ResultType.INVALID);
        }

        if (clip.getDuration() <= 0) {
            result.addMessage("duration must be > 0", ResultType.INVALID);
        }

        if (clip.getVolume() < 0 || clip.getVolume() > 1) {
            result.addMessage("volume must be between 0 and 1", ResultType.INVALID);
        }

        if (clip.getPlaybackSpeed() < 0.25 || clip.getPlaybackSpeed() > 4.0) {
            result.addMessage("playbackSpeed must be between 0.25 and 4.0", ResultType.INVALID);
        }

        if (clip.getDiscordUserId() <= 0) {
            result.addMessage("discordUserId must be > 0", ResultType.INVALID);
        }
    }

    public Result<Void> addPlaylist(ClipPlaylist clipPlaylist) {
        Result<Void> result = validate(clipPlaylist);
        if (!result.isSuccess()) {
            return result;
        }

        try {
            if (!clipPlaylistRepository.add(clipPlaylist)) {
                result.addMessage("playlist not added", ResultType.INVALID);
            }
        } catch (DuplicateKeyException e) {
            result.addMessage("clip already has playlist", ResultType.CONFLICT);
        } catch (DataIntegrityViolationException e) {
            result.addMessage("clip or playlist does not exist", ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deletePlaylistByKey(int clipId, int playlistId) {
        return clipPlaylistRepository.deleteByKey(clipId, playlistId);
    }

    private Result<Void> validate(ClipPlaylist clipPlaylist) {
        Result<Void> result = new Result<>();

        validateClipPlaylist(clipPlaylist, result);
        if (!result.isSuccess()) {
            return result;
        }

        return result;
    }

    private void validateClipPlaylist(ClipPlaylist clipPlaylist, Result<Void> result) {
        if (clipPlaylist == null) {
            result.addMessage("clipPlaylist cannot be null", ResultType.INVALID);
            return;
        }

        if (clipPlaylist.getPlaylist() == null) {
            result.addMessage("playlist cannot be null", ResultType.INVALID);
        }

        if (clipPlaylist.getDisplayOrder() < 1) {
            result.addMessage("displayOrder must be > 0", ResultType.INVALID);
        }
    }
}
