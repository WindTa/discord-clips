package org.capstone.controllers;

import org.capstone.domain.ClipService;
import org.capstone.domain.PlaylistService;
import org.capstone.domain.Result;
import org.capstone.models.ClipPlaylist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins={"http://localhost:3000"})
@RequestMapping("/api/clips/playlists")
public class ClipPlaylistController {

    private final ClipService service;

    public ClipPlaylistController(ClipService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody ClipPlaylist clipPlaylist) {
        Result<Void> result = service.addPlaylist(clipPlaylist);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{clipId}/{playlistId}")
    public ResponseEntity<Void> deleteByKey(@PathVariable int clipId, @PathVariable int playlistId) {
        if (service.deletePlaylistByKey(clipId, playlistId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
