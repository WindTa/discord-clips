package org.capstone.domain;

import org.capstone.data.interfaces.DiscordServerClipRepository;
import org.capstone.data.interfaces.DiscordServerRepository;
import org.capstone.models.DiscordServer;
import org.capstone.models.DiscordServerClip;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class DiscordServerService {

    private final DiscordServerRepository discordServerRepository;
    private final DiscordServerClipRepository discordServerClipRepository;

    public DiscordServerService(DiscordServerRepository discordServerRepository,
                                DiscordServerClipRepository discordServerClipRepository) {
        this.discordServerRepository = discordServerRepository;
        this.discordServerClipRepository = discordServerClipRepository;
    }

    public DiscordServer findById(long discordServerId) {
        return discordServerRepository.findById(discordServerId);
    }

    public Result<DiscordServer> add(DiscordServer discordServer) {
        Result<DiscordServer> result = validate(discordServer);
        if (!result.isSuccess()) {
            return result;
        }

        DiscordServer existingDiscordServer = discordServerRepository.findById(discordServer.getDiscordServerId());
        if (existingDiscordServer != null
                && existingDiscordServer.getDiscordServerId() == discordServer.getDiscordServerId()) {
            result.addMessage("server id must be unique", ResultType.INVALID);
            return result;
        }

        discordServer = discordServerRepository.add(discordServer);
        result.setPayload(discordServer);
        return result;
    }

    public Result<DiscordServer> update(DiscordServer discordServer) {
        Result<DiscordServer> result = validate(discordServer);
        if (!result.isSuccess()) {
            return result;
        }

        if (!discordServerRepository.update(discordServer)) {
            String msg = String.format("discordServerId: %s, not found", discordServer.getDiscordServerId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(long discordServerId) {
        return discordServerRepository.deleteById(discordServerId);
    }

    private Result<DiscordServer> validate(DiscordServer discordServer) {
        Result<DiscordServer> result = new Result<>();

        validateDiscordServer(discordServer, result);
        if (!result.isSuccess()) {
            return result;
        }

        return result;
    }

    private void validateDiscordServer(DiscordServer discordServer, Result<DiscordServer> result) {
        if (discordServer == null) {
            result.addMessage("discord server cannot be null", ResultType.INVALID);
            return;
        }

        if (discordServer.getDiscordServerId() < 0) {
            result.addMessage("discordServerId must be >= 0", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(discordServer.getServername())) {
            result.addMessage("servername is required", ResultType.INVALID);
        }
    }

    public Result<Void> addClip(DiscordServerClip discordServerClip) {
        Result<Void> result = validate(discordServerClip);
        if (!result.isSuccess()) {
            return result;
        }

        try {
            if (!discordServerClipRepository.add(discordServerClip)) {
                result.addMessage("clip not added", ResultType.INVALID);
            }
        } catch (DuplicateKeyException e) {
            result.addMessage("server already has clip", ResultType.CONFLICT);
        } catch (DataIntegrityViolationException e) {
            result.addMessage("server or clip does not exist", ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteClipByKey(long discordServerId, int clipId) {
        return discordServerClipRepository.deleteByKey(discordServerId, clipId);
    }

    private Result<Void> validate(DiscordServerClip discordServerClip) {
        Result<Void> result = new Result<>();

        validateDiscordServerClip(discordServerClip, result);
        if (!result.isSuccess()) {
            return result;
        }

        return result;
    }

    private void validateDiscordServerClip(DiscordServerClip discordServerClip, Result<Void> result) {
        if (discordServerClip == null) {
            result.addMessage("discordServerClip cannot be null", ResultType.INVALID);
            return;
        }

        if (discordServerClip.getClip() == null) {
            result.addMessage("clip cannot be null", ResultType.INVALID);
        }
    }
}
