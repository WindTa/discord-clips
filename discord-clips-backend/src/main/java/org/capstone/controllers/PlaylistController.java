package org.capstone.controllers;

import org.capstone.domain.PlaylistService;
import org.capstone.domain.Result;
import org.capstone.models.Playlist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins={"http://localhost:3000"})
@RequestMapping("/api/playlists")
public class PlaylistController {
    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/discord-user/{discordUserId}")
    public ResponseEntity<List<Playlist>> findByDiscordUserId(@PathVariable long discordUserId) {
        List<Playlist> playlists = playlistService.findByDiscordUserId(discordUserId);
        if (playlists.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(playlists);
    }

    @GetMapping("/{playlistId}")
    public ResponseEntity<Playlist> findById(@PathVariable int playlistId) {
        Playlist playlist = playlistService.findById(playlistId);
        if (playlist == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(playlist);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Playlist playlist) {
        Result<Playlist> result = playlistService.add(playlist);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{playlistId}")
    public ResponseEntity<Object> update(@PathVariable int playlistId, @RequestBody Playlist playlist) {
        if (playlistId != playlist.getPlaylistId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Playlist> result = playlistService.update(playlist);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{playlistId}")
    public ResponseEntity<Void> deleteById(@PathVariable int playlistId) {
        if (playlistService.deleteById(playlistId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
