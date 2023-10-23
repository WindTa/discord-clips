package org.capstone.domain;

import org.capstone.data.interfaces.ClipRepository;
import org.capstone.data.interfaces.DiscordUserRepository;
import org.capstone.data.interfaces.PlaylistClipRepository;
import org.capstone.data.interfaces.PlaylistRepository;
import org.capstone.models.Clip;
import org.capstone.models.Playlist;
import org.capstone.models.PlaylistClip;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    private final DiscordUserRepository discordUserRepository;
    private final PlaylistRepository playlistRepository;
    private final PlaylistClipRepository playlistClipRepository;
    private final ClipRepository clipRepository;

    public PlaylistService(DiscordUserRepository discordUserRepository, PlaylistRepository playlistRepository,
                           PlaylistClipRepository playlistClipRepository, ClipRepository clipRepository) {
        this.discordUserRepository = discordUserRepository;
        this.playlistRepository = playlistRepository;
        this.playlistClipRepository = playlistClipRepository;
        this.clipRepository = clipRepository;
    }

    public List<Playlist> findByDiscordUserId(long discordUserId) {
        return playlistRepository.findByDiscordUserId(discordUserId);
    }

    public Playlist findById(int playlistId) {
        return playlistRepository.findById(playlistId);
    }

    public Result<Playlist> add(Playlist playlist) {
        Result<Playlist> result = validate(playlist);
        if (!result.isSuccess()) {
            return result;
        }

        if (playlist.getPlaylistId() != 0) {
            result.addMessage("playlistId must be set for `add` operation", ResultType.INVALID);
            return result;
        }

        playlist = playlistRepository.add(playlist);
        result.setPayload(playlist);
        return result;
    }

    public Result<Playlist> update(Playlist playlist) {
        Result<Playlist> result = validate(playlist);
        if (!result.isSuccess()) {
            return result;
        }

        if (playlist.getPlaylistId() <= 0) {
            result.addMessage("playlistId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!playlistRepository.update(playlist)) {
            String msg = String.format("playlist does not exist", playlist.getPlaylistId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int playlistId) {
        return playlistRepository.deleteById(playlistId);
    }

    private Result<Playlist> validate(Playlist playlist) {
        Result<Playlist> result = new Result<>();

        validatePlaylist(playlist, result);
        if (!result.isSuccess()) {
            return result;
        }

        if (discordUserRepository.findById(playlist.getDiscordUserId()) == null) {
            result.addMessage("discord user does not exist", ResultType.NOT_FOUND);
        }

        return result;
    }

    private void validatePlaylist(Playlist playlist, Result<Playlist> result) {
        if (playlist == null) {
            result.addMessage("playlist cannot be null", ResultType.INVALID);
            return;
        }

        if (playlist.getPlaylistId() < 0) {
            result.addMessage("playlistId must be >= 0", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(playlist.getPlaylistName())) {
            result.addMessage("playlistName is required", ResultType.INVALID);
        }

        if (playlist.getDiscordUserId() <= 0) {
            result.addMessage("discordUserId must be > 0", ResultType.INVALID);
        }
    }

    public Result<Void> addClip(PlaylistClip playlistClip) {
        Result<Void> result = validate(playlistClip);
        if (!result.isSuccess()) {
            return result;
        }

        try {
            if (!playlistClipRepository.add(playlistClip)) {
                result.addMessage("clip not added", ResultType.INVALID);
            }
        } catch (DuplicateKeyException e) {
            result.addMessage("playlist already has clip", ResultType.CONFLICT);
        } catch (DataIntegrityViolationException e) {
            result.addMessage("playlist or clip does not exist", ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteClipByKey(int playlistId, int clipId) {
        return playlistClipRepository.deleteByKey(playlistId, clipId);
    }

    private Result<Void> validate(PlaylistClip playlistClip) {
        Result<Void> result = new Result<>();

        validatePlaylistClip(playlistClip, result);
        if (!result.isSuccess()) {
            return result;
        }

        return result;
    }

    private void validatePlaylistClip(PlaylistClip playlistClip, Result<Void> result) {
        if (playlistClip == null) {
            result.addMessage("playlistClip cannot be null", ResultType.INVALID);
            return;
        }

        if (playlistClip.getClip() == null) {
            result.addMessage("clip cannot be null", ResultType.INVALID);
        }

        if (playlistClip.getDisplayOrder() < 1) {
            result.addMessage("displayOrder must be > 0", ResultType.INVALID);
        }
    }
}
