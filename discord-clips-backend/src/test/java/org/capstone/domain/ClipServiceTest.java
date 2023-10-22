package org.capstone.domain;

import org.capstone.data.interfaces.*;
import org.capstone.models.Clip;
import org.capstone.models.ClipPlaylist;
import org.capstone.models.Playlist;
import org.capstone.models.PlaylistClip;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.capstone.DataHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ClipServiceTest {

    @Autowired
    ClipService service;

    @MockBean
    DiscordUserRepository discordUserRepository;

    @MockBean
    PlaylistRepository playlistRepository;

    @MockBean
    ClipRepository clipRepository;

    @MockBean
    ClipPlaylistRepository clipPlaylistRepository;

    @Test
    void shouldAddClip() {
        when(discordUserRepository.findById(anyLong())).thenReturn(makeWindTaUser());

        Clip clip = makeWindTaClip();
        when(clipRepository.add(any())).thenReturn(clip);

        // Add when ID set for add
        clip.setClipId(0);
        Result<Clip> result = service.add(clip);

        // See if success with new ID
        clip.setClipId(1);
        assertEquals(0, result.getMessages().size());
        assertEquals(clip, result.getPayload());
    }

    @Test
    void shouldNotAddClipWhenInvalid() {
        Clip clip = makeWindTaClip();
        when(discordUserRepository.findById(anyLong())).thenReturn(makeWindTaUser());

        // Check clip exists
        Result<Clip> result = service.add(null);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("clip cannot be null", result.getMessages().get(0));

        // Check if clipId is set for add
        clip.setClipId(1);
        result = service.add(clip);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("clipId must be set for `add` operation", result.getMessages().get(0));

        // Check if discordUserId is valid
        clip.setDiscordUserId(0);
        result = service.add(clip);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("discordUserId must be > 0", result.getMessages().get(0));

        // Check if playback speed between [0.25, 4.0]
        clip.setPlaybackSpeed(4.1);
        result = service.add(clip);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("playbackSpeed must be between 0.25 and 4.0", result.getMessages().get(0));

        // Check if volume between [0, 1]
        clip.setVolume(1.1);
        result = service.add(clip);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("volume must be between 0 and 1", result.getMessages().get(0));

        // Check if duration > 0
        clip.setDuration(0);
        result = service.add(clip);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("duration must be > 0", result.getMessages().get(0));

        // Check if start time >= 0
        clip.setStartTime(-0.1);
        result = service.add(clip);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("startTime must be >= 0", result.getMessages().get(0));

        // Check if youtubeId not null
        clip.setYoutubeId(null);
        result = service.add(clip);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("youtubeId is required", result.getMessages().get(0));

        // Check if youtubeId not null
        clip.setYoutubeId("");
        result = service.add(clip);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("youtubeId is required", result.getMessages().get(0));

        // Check if clip name not null or blank
        clip.setClipName(null);
        result = service.add(clip);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("clipName is required", result.getMessages().get(0));

        // Check if clip name not null or blank
        clip.setClipName("");
        result = service.add(clip);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("clipName is required", result.getMessages().get(0));

        // Check if clip id >= 0
        clip.setClipId(-1);
        result = service.add(clip);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("clipId must be >= 0", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddClipWhenConstraintErrors() {
        when(discordUserRepository.findById(anyLong())).thenReturn(null);
        Result<Clip> result = service.add(makeWindTaClip());
        assertEquals(ResultType.NOT_FOUND, result.getType());
        assertEquals("discord user does not exist", result.getMessages().get(0));
    }

    @Test
    void shouldUpdateClip() {
        Clip clip = makeWindTaClip();
        when(discordUserRepository.findById(anyLong())).thenReturn(makeWindTaUser());
        when(clipRepository.update(any())).thenReturn(true);
        Result<Clip> result = service.update(clip);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(0, result.getMessages().size());

        // Should be INVALID when not set for update
        clip.setClipId(0);
        result = service.update(clip);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("clipId must be set for `update` operation", result.getMessages().get(0));
    }

    @Test
    void shouldAddClipPlaylist() {
        when(clipRepository.findById(anyInt())).thenReturn(makeWindTaClipWithPlaylists());
        when(playlistRepository.findById(anyInt())).thenReturn(makeWindTasPlaylistWithClips());

        // Success!
        when(clipPlaylistRepository.add(any())).thenReturn(true);
        ClipPlaylist clipPlaylist = makeWindTaClipPlaylist();
        clipPlaylist.getPlaylist().setPlaylistId(2);
        Result<Void> result = service.addPlaylist(clipPlaylist);
        assertEquals(0, result.getMessages().size());

        // When it somehow fails?
        when(clipPlaylistRepository.add(clipPlaylist)).thenReturn(false);
        result = service.addPlaylist(clipPlaylist);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("playlist not added", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddClipPlaylistWhenInvalid() {
        // Check if clipPlaylist exists
        Result<Void> result = service.addPlaylist(null);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("clipPlaylist cannot be null", result.getMessages().get(0));

        // Check if displayOrder is > 0
        ClipPlaylist clipPlaylist = makeWindTaClipPlaylist();
        clipPlaylist.setDisplayOrder(0);
        result = service.addPlaylist(clipPlaylist);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("displayOrder must be > 0", result.getMessages().get(0));

        // Check if playlist exists
        clipPlaylist.setPlaylist(null);
        result = service.addPlaylist(clipPlaylist);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("playlist cannot be null", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddClipPlaylistWhenConstraintErrors() {
        ClipPlaylist clipPlaylist = makeWindTaClipPlaylist();

        // When clip does not exist
        when(clipRepository.findById(anyInt())).thenReturn(null);
        Result<Void> result = service.addPlaylist(clipPlaylist);
        assertEquals(ResultType.NOT_FOUND, result.getType());
        assertEquals("clip does not exist", result.getMessages().get(0));

        // When playlist does not exist
        when(clipRepository.findById(anyInt())).thenReturn(makeWindTaClipWithPlaylists());
        when(playlistRepository.findById(anyInt())).thenReturn(null);
        result = service.addPlaylist(makeWindTaClipPlaylist());
        assertEquals(ResultType.NOT_FOUND, result.getType());
        assertEquals("playlist does not exist", result.getMessages().get(0));

        // When the clip already has the playlist
        when(playlistRepository.findById(anyInt())).thenReturn(makeWindTasPlaylistWithClips());
        result = service.addPlaylist(makeWindTaClipPlaylist());
        assertEquals(ResultType.CONFLICT, result.getType());
        assertEquals("clip already has playlist", result.getMessages().get(0));
    }
}
