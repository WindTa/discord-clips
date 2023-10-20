package org.capstone.domain;

import org.capstone.data.interfaces.DiscordServerRepository;
import org.capstone.models.DiscordServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DiscordServerServiceTest {
    @Autowired
    DiscordServerService discordServerService;

    @MockBean
    DiscordServerRepository discordServerRepository;

    @Test
    void shouldFindWindTa() {
        DiscordServer expected = makeDiscordServer();
        when(discordServerRepository.findById(expected.getDiscordServerId())).thenReturn(expected);
        DiscordServer actual = discordServerService.findById(expected.getDiscordServerId());
        assertEquals(expected, actual);
    }

    @Test
    void shouldAddWhenValid() {
        DiscordServer expected = makeDiscordServer();
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

        discordServer = makeDiscordServer();
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
        DiscordServer discordServer = makeDiscordServer();
        discordServer.setServername("New");

        when(discordServerRepository.update(discordServer)).thenReturn(true);
        Result<DiscordServer> actual = discordServerService.update(discordServer);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateMissing() {
        DiscordServer discordServer = makeDiscordServer();
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

        discordServer = makeDiscordServer();
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


    private DiscordServer makeDiscordServer() {
        DiscordServer discordServer = new DiscordServer();
        discordServer.setDiscordServerId(1161381438839607358L);
        discordServer.setServername("WindTa's server");
        return discordServer;
    }
}
