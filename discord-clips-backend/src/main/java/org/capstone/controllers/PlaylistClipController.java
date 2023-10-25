package org.capstone.controllers;

import org.capstone.domain.PlaylistService;
import org.capstone.domain.Result;
import org.capstone.models.PlaylistClip;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins={"http://localhost:3000"})
@RequestMapping("/api/playlists/clips")
public class PlaylistClipController {

    private final PlaylistService service;

    public PlaylistClipController(PlaylistService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody PlaylistClip playlistClip) {
        Result<Void> result = service.addClip(playlistClip);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{playlistId}/{clipId}")
    public ResponseEntity<Void> deleteByKey(@PathVariable int playlistId, @PathVariable int clipId) {
        if (service.deleteClipByKey(playlistId, clipId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
