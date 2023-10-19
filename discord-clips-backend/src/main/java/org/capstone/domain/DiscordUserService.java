package org.capstone.domain;

import org.capstone.data.DiscordUserRepository;
import org.capstone.models.DiscordUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscordUserService {

    private final DiscordUserRepository discordUserRepository;

    public DiscordUserService(DiscordUserRepository discordUserRepository) {
        this.discordUserRepository = discordUserRepository;
    }

    public DiscordUser findById(long discordUserId) {
        return discordUserRepository.findById(discordUserId);
    }

    public Result<DiscordUser> add(DiscordUser discordUser) {
        Result<DiscordUser> result = validate(discordUser);
        if (!result.isSuccess()) {
            return result;
        }

        DiscordUser existingDiscordUser = discordUserRepository.findById(discordUser.getDiscordUserId());
        if (existingDiscordUser != null
                && existingDiscordUser.getDiscordUserId() == discordUser.getDiscordUserId()) {
            result.addMessage("user id must be unique", ResultType.INVALID);
            return result;
        }

        discordUser = discordUserRepository.add(discordUser);
        result.setPayload(discordUser);
        return result;
    }

    public Result<DiscordUser> update(DiscordUser discordUser) {
        Result<DiscordUser> result = validate(discordUser);
        if (!result.isSuccess()) {
            return result;
        }

        if (!discordUserRepository.update(discordUser)) {
            String msg = String.format("discordUserId: %s, not found", discordUser.getDiscordUserId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(long discordUserId) {
        return discordUserRepository.deleteById(discordUserId);
    }

    private Result<DiscordUser> validate(DiscordUser discordUser) {
        Result<DiscordUser> result = new Result<>();

        validateDiscordUser(discordUser, result);
        if (!result.isSuccess()) {
            return result;
        }

        return result;
    }

    private void validateDiscordUser(DiscordUser discordUser, Result<DiscordUser> result) {
        if (discordUser == null) {
            result.addMessage("discord user cannot be null", ResultType.INVALID);
            return;
        }

        if (discordUser.getDiscordUserId() < 0) {
            result.addMessage("discordUserId must be >= 0", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(discordUser.getUsername())) {
            result.addMessage("username is required", ResultType.INVALID);
        }
    }
}
