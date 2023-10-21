package org.capstone.controllers;

import org.apache.coyote.Response;
import org.capstone.domain.DiscordUserService;
import org.capstone.domain.Result;
import org.capstone.models.DiscordUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/discord-users")
public class DiscordUserController {
    private final DiscordUserService discordUserService;

    public DiscordUserController(DiscordUserService discordUserService) {
        this.discordUserService = discordUserService;
    }

    @GetMapping("/{discordUserId}")
    public ResponseEntity<Object> findById(@PathVariable long discordUserId) {
        DiscordUser discordUser = discordUserService.findById(discordUserId);

        if (discordUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(discordUser);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody DiscordUser discordUser) {
        Result<DiscordUser> result = discordUserService.add(discordUser);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{discordUserId}")
    public ResponseEntity<Object> update(@PathVariable long discordUserId, @RequestBody DiscordUser discordUser) {
        if (discordUserId != discordUser.getDiscordUserId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<DiscordUser> result = discordUserService.update(discordUser);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{discordUserId}")
    public ResponseEntity<Void> deleteById(@PathVariable long discordUserId) {
        if (discordUserService.deleteById(discordUserId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
