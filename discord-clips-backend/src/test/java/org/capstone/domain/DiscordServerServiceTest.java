package org.capstone.domain;

import org.capstone.data.interfaces.ClipRepository;
import org.capstone.data.interfaces.DiscordServerClipRepository;
import org.capstone.data.interfaces.DiscordServerRepository;
import org.capstone.models.DiscordServer;
import org.capstone.models.DiscordServerClip;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

import static org.capstone.DataHelper.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DiscordServerServiceTest {
    @Autowired
    DiscordServerService discordServerService;

    @MockBean
    DiscordServerRepository discordServerRepository;

    @MockBean
    ClipRepository clipRepository;

    @MockBean
    DiscordServerClipRepository discordServerClipRepository;


    @Test
    void shouldFindWindTa() {
        DiscordServer expected = makeWindTaServer();
        when(discordServerRepository.findById(expected.getDiscordServerId())).thenReturn(expected);
        DiscordServer actual = discordServerService.findById(expected.getDiscordServerId());
        assertEquals(expected, actual);
    }

    @Test
    void shouldAddWhenValid() {
        DiscordServer expected = makeWindTaServer();
        expected.setDiscordServerId(expected.getDiscordServerId()+1);

        when(discordServerRepository.add(expected)).thenReturn(expected);
        Result<DiscordServer> result = discordServerService.add(expected);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(expected, result.getPayload());
    }

    @Test
    void shouldNotAddWhenDiscordServerInvalid() {
        DiscordServer discordServer = null;
        Result<DiscordServer> result = discordServerService.add(discordServer);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("discord server cannot be null", result.getMessages().get(0));

        discordServer = makeWindTaServer();
        when(discordServerRepository.findById(discordServer.getDiscordServerId())).thenReturn(discordServer);
        result = discordServerService.add(discordServer);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("server id must be unique", result.getMessages().get(0));

        discordServer.setServername(null);
        result = discordServerService.add(discordServer);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("servername is required", result.getMessages().get(0));

        discordServer.setDiscordServerId(-1);
        result = discordServerService.add(discordServer);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("discordServerId must be >= 0", result.getMessages().get(0));

    }

    @Test
    void shouldUpdate() {
        DiscordServer discordServer = makeWindTaServer();
        discordServer.setServername("New");

        when(discordServerRepository.update(discordServer)).thenReturn(true);
        Result<DiscordServer> actual = discordServerService.update(discordServer);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateMissing() {
        DiscordServer discordServer = makeWindTaServer();
        discordServer.setDiscordServerId(0);

        when(discordServerRepository.update(discordServer)).thenReturn(false);
        Result<DiscordServer> actual = discordServerService.update(discordServer);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

    @Test
    void shouldNotUpdateWhenInvalid() {
        DiscordServer discordServer = null;
        Result<DiscordServer> actual = discordServerService.update(discordServer);
        assertEquals(ResultType.INVALID, actual.getType());
        assertEquals("discord server cannot be null", actual.getMessages().get(0));

        discordServer = makeWindTaServer();
        discordServer.setServername(null);
        actual = discordServerService.update(discordServer);
        assertEquals(ResultType.INVALID, actual.getType());
        assertEquals("servername is required", actual.getMessages().get(0));
    }

    @Test
    void shouldDeleteWhenFound() {
        when(discordServerRepository.deleteById(1)).thenReturn(true);
        assertTrue(discordServerService.deleteById(1));
    }

    @Test
    void shouldDeleteWhenNotFound() {
        when(discordServerRepository.deleteById(1)).thenReturn(false);
        assertFalse(discordServerService.deleteById(1));
    }

    @Test
    void shouldAddDiscordServerClip() {
        when(discordServerRepository.findById(anyInt())).thenReturn(makeWindTaServerWithClips());
        when(clipRepository.findById(anyInt())).thenReturn(makeWindTaClipWithPlaylists());

        // Success!
        when(discordServerClipRepository.add(any())).thenReturn(true);
        DiscordServerClip discordServerClip = makeWindTaDiscordServerClip();
        discordServerClip.getClip().setClipId(2);
        Result<Void> result = discordServerService.addClip(discordServerClip);
        assertEquals(0, result.getMessages().size());

        //When it somehow fails?
        when(discordServerClipRepository.add(any())).thenReturn(false);
        result = discordServerService.addClip(discordServerClip);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("clip not added", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddDiscordServerClipWhenInvalid() {
        // Check if discordServerClip exists
        Result<Void> result = discordServerService.addClip(null);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("discordServerClip cannot be null", result.getMessages().get(0));

        // Check if clip is null
        DiscordServerClip discordServerClip = makeWindTaDiscordServerClip();
        discordServerClip.setClip(null);
        result = discordServerService.addClip(discordServerClip);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("clip cannot be null", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddDiscordServerClipWhenConstraintErrors() {
        DiscordServerClip discordServerClip = makeWindTaDiscordServerClip();

        // When server or clip does not exist
        when(discordServerClipRepository.add(discordServerClip)).thenThrow(DataIntegrityViolationException.class);
        Result<Void> result = discordServerService.addClip(discordServerClip);
        assertEquals(ResultType.NOT_FOUND, result.getType());
        assertEquals("server or clip does not exist", result.getMessages().get(0));

        // When server already has the clip
        when(discordServerClipRepository.add(any())).thenThrow(DuplicateKeyException.class);
        result = discordServerService.addClip(discordServerClip);
        assertEquals(ResultType.CONFLICT, result.getType());
        assertEquals("server already has clip", result.getMessages().get(0));
    }
}

