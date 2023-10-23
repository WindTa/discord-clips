package org.capstone.domain;

import static org.capstone.DataHelper.*;
import org.capstone.data.interfaces.ClipRepository;
import org.capstone.data.interfaces.DiscordUserRepository;
import org.capstone.data.interfaces.PlaylistClipRepository;
import org.capstone.data.interfaces.PlaylistRepository;
import org.capstone.models.Playlist;
import org.capstone.models.PlaylistClip;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PlaylistServiceTest {

    @Autowired
    PlaylistService service;

    @MockBean
    DiscordUserRepository discordUserRepository;

    @MockBean
    PlaylistRepository playlistRepository;

    @MockBean
    ClipRepository clipRepository;

    @MockBean
    PlaylistClipRepository playlistClipRepository;

    @Test
    void shouldAddPlaylist() {
        when(discordUserRepository.findById(anyLong())).thenReturn(makeWindTaUser());

        Playlist playlist = makeWindTasPlaylist();
        when(playlistRepository.add(any())).thenReturn(playlist);

        // Add when ID set for add
        playlist.setPlaylistId(0);
        Result<Playlist> result = service.add(playlist);

        // See if success with new ID
        playlist.setPlaylistId(1);
        assertEquals(0, result.getMessages().size());
        assertEquals(playlist, result.getPayload());

    }

    @Test
    void shouldNotAddPlaylistWhenInvalid() {
        Playlist playlist = makeWindTasPlaylist();
        when(discordUserRepository.findById(anyLong())).thenReturn(makeWindTaUser());

        // Check if playlist exists
        Result<Playlist> result = service.add(null);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("playlist cannot be null", result.getMessages().get(0));

        // Check if playlistId is set for add
        playlist.setPlaylistId(1);
        result = service.add(playlist);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("playlistId must be set for `add` operation", result.getMessages().get(0));

        // Check if discordUserId is valid
        playlist.setDiscordUserId(0);
        result = service.add(playlist);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("discordUserId must be > 0", result.getMessages().get(0));

        // Check if playlist name is null
        playlist.setPlaylistName(null);
        result = service.add(playlist);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("playlistName is required", result.getMessages().get(0));

        // Check if playlist name is empty
        playlist.setPlaylistName("");
        result = service.add(playlist);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("playlistName is required", result.getMessages().get(0));

        // Check if playlistId is valid
        playlist.setPlaylistId(-1);
        result = service.add(playlist);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("playlistId must be >= 0", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddPlaylistWhenConstraintErrors() {
        when(discordUserRepository.findById(anyLong())).thenReturn(null);
        Result<Playlist> result = service.add(makeWindTasPlaylist());
        assertEquals(ResultType.NOT_FOUND, result.getType());
        assertEquals("discord user does not exist", result.getMessages().get(0));
    }

    @Test
    void shouldUpdatePlaylist() {
        Playlist playlist = makeWindTasPlaylist();
        when(discordUserRepository.findById(anyLong())).thenReturn(makeWindTaUser());
        when(playlistRepository.update(any())).thenReturn(true);
        Result<Playlist> result = service.update(playlist);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(0, result.getMessages().size());

        // Should be INVALID when not set for update
        playlist.setPlaylistId(0);
        result = service.update(playlist);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("playlistId must be set for `update` operation", result.getMessages().get(0));
    }

    @Test
    void shouldAddPlaylistClip() {
        when(playlistRepository.findById(anyInt())).thenReturn(makeWindTasPlaylistWithClips());
        when(clipRepository.findById(anyInt())).thenReturn(makeWindTaClipWithPlaylists());

        // Success!
        when(playlistClipRepository.add(any())).thenReturn(true);
        PlaylistClip playlistClip = makeWindTaPlaylistClip();
        playlistClip.getClip().setClipId(2);
        Result<Void> result = service.addClip(playlistClip);
        assertEquals(0, result.getMessages().size());

        // When it somehow fails?
        when(playlistClipRepository.add(playlistClip)).thenReturn(false);
        result = service.addClip(playlistClip);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("clip not added", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddPlaylistClipWhenInvalid() {
        // Check if playlistClip exists
        Result<Void> result = service.addClip(null);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("playlistClip cannot be null", result.getMessages().get(0));

        // Check if displayOrder is > 0
        PlaylistClip playlistClip = makeWindTaPlaylistClip();
        playlistClip.setDisplayOrder(0);
        result = service.addClip(playlistClip);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("displayOrder must be > 0", result.getMessages().get(0));

        // Check if clip exists
        playlistClip.setClip(null);
        result = service.addClip(playlistClip);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("clip cannot be null", result.getMessages().get(0));

    }

    @Test
    void shouldNotAddPlaylistClipWhenConstraintErrors() {
        PlaylistClip playlistClip = makeWindTaPlaylistClip();

        // When clip or playlist does not exist
        when(playlistClipRepository.add(playlistClip)).thenThrow(DataIntegrityViolationException.class);
        Result<Void> result = service.addClip(playlistClip);
        assertEquals(ResultType.NOT_FOUND, result.getType());
        assertEquals("playlist or clip does not exist", result.getMessages().get(0));

        // When the playlist already has the clip
        when(playlistClipRepository.add(any())).thenThrow(DuplicateKeyException.class);
        result = service.addClip(playlistClip);
        assertEquals(ResultType.CONFLICT, result.getType());
        assertEquals("playlist already has clip", result.getMessages().get(0));

    }

}