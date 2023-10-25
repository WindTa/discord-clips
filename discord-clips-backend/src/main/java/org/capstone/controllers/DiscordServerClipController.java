package org.capstone.controllers;

import org.capstone.domain.DiscordServerService;
import org.capstone.domain.Result;
import org.capstone.models.DiscordServerClip;
import org.capstone.models.PlaylistClip;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins={"http://localhost:3000"})
@RequestMapping("/api/discord-servers/clips")
public class DiscordServerClipController {

    private final DiscordServerService service;

    public DiscordServerClipController(DiscordServerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody DiscordServerClip discordServerClip) {
        Result<Void> result = service.addClip(discordServerClip);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{discordServerId}/{clipId}")
    public ResponseEntity<Void> deleteByKey(@PathVariable long discordServerId, @PathVariable int clipId) {
        if (service.deleteClipByKey(discordServerId, clipId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
