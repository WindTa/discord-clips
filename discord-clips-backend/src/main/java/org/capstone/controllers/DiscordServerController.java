package org.capstone.controllers;

import org.capstone.domain.DiscordServerService;
import org.capstone.domain.Result;
import org.capstone.models.DiscordServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/discord-servers")
public class DiscordServerController {
    private final DiscordServerService discordServerService;

    public DiscordServerController(DiscordServerService discordServerService) {
        this.discordServerService = discordServerService;
    }

    @GetMapping("/{discordServerId}")
    public ResponseEntity<Object> findById(@PathVariable long discordServerId) {
        DiscordServer discordServer = discordServerService.findById(discordServerId);

        if (discordServer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(discordServer);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody DiscordServer discordServer) {
        Result<DiscordServer> result = discordServerService.add(discordServer);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{discordServerId}")
    public ResponseEntity<Object> update(@PathVariable long discordServerId, @RequestBody DiscordServer discordServer) {
        if (discordServerId != discordServer.getDiscordServerId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<DiscordServer> result = discordServerService.update(discordServer);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{discordServerId}")
    public ResponseEntity<Void> deleteById(@PathVariable long discordServerId) {
        if (discordServerService.deleteById(discordServerId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
