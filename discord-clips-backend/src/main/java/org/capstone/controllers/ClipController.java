package org.capstone.controllers;

import org.capstone.domain.ClipService;
import org.capstone.domain.Result;
import org.capstone.models.Clip;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins={"http://localhost:3000"})
@RequestMapping("/api/clips")
public class ClipController {
    private final ClipService clipService;

    public ClipController(ClipService clipService) {
        this.clipService = clipService;
    }

    @GetMapping("/discord-user/{discordUserId}")
    public ResponseEntity<List<Clip>> findByDiscordUserId(@PathVariable long discordUserId) {
        List<Clip> clips = clipService.findByDiscordUserId(discordUserId);
        if (clips.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(clips);
    }

    @GetMapping("/{clipId}")
    public ResponseEntity<Clip> findById(@PathVariable int clipId) {
        Clip clip = clipService.findById(clipId);
        if (clip == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(clip);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Clip clip) {
        Result<Clip> result = clipService.add(clip);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{clipId}")
    public ResponseEntity<Object> update(@PathVariable int clipId, @RequestBody Clip clip) {
        if (clipId != clip.getClipId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Clip> result = clipService.update(clip);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{clipId}")
    public ResponseEntity<Void> deleteById(@PathVariable int clipId) {
        if (clipService.deleteById(clipId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
