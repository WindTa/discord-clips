package org.capstone.data;

import org.capstone.data.jdbc.DiscordServerJdbcTemplateRepository;
import org.capstone.models.DiscordServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class DiscordServerJdbcTemplateRepositoryTest {

    @Autowired
    DiscordServerJdbcTemplateRepository discordServerRepository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void findById() {
        DiscordServer expected = makeDiscordServer();
        DiscordServer actual = discordServerRepository.findById(expected.getDiscordServerId());
        assertEquals(expected.getDiscordServerId(), actual.getDiscordServerId());
        assertEquals(expected.getServername(), actual.getServername());

        DiscordServer invalid = discordServerRepository.findById(234124235L);
        assertNull(invalid);
    }

    @Test
    void shouldAdd() {
        DiscordServer discordServer = makeDiscordServer();
        discordServer.setDiscordServerId(discordServer.getDiscordServerId()+1);
        DiscordServer actual = discordServerRepository.add(discordServer);
        assertNotNull(actual);
        assertEquals(discordServer.getDiscordServerId(), actual.getDiscordServerId());
    }

    @Test
    void shouldUpdate() {
        DiscordServer discordServer = makeDiscordServer();
        discordServer.setServername("New");

        // Check if update was successful
        assertTrue(discordServerRepository.update(discordServer));

        // Double check server was updated
        DiscordServer actual = discordServerRepository.findById(discordServer.getDiscordServerId());
        assertEquals(discordServer, actual);

        // Unhappy path
        discordServer.setDiscordServerId(-1);
        assertFalse(discordServerRepository.update(discordServer));
    }

    @Test
    void shouldDelete() {
        DiscordServer discordServer = makeDiscordServer();
        assertTrue(discordServerRepository.deleteById(discordServer.getDiscordServerId()));
        assertFalse(discordServerRepository.deleteById(discordServer.getDiscordServerId()));
    }

    private DiscordServer makeDiscordServer() {
        DiscordServer discordServer = new DiscordServer();
        discordServer.setDiscordServerId(1161381438839607358L);
        discordServer.setServername("WindTa's server");

        return discordServer;
    }
}