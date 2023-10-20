package org.capstone.domain;

import org.capstone.data.interfaces.DiscordServerRepository;
import org.capstone.models.DiscordServer;
import org.springframework.stereotype.Service;

@Service
public class DiscordServerService {

    private final DiscordServerRepository discordServerRepository;

    public DiscordServerService(DiscordServerRepository discordServerRepository) {
        this.discordServerRepository = discordServerRepository;
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
}
