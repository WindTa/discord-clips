package org.capstone.data.jdbc;

import org.capstone.DataHelper;
import org.capstone.data.KnownGoodState;
import org.capstone.data.jdbc.DiscordServerJdbcTemplateRepository;
import org.capstone.models.DiscordServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.capstone.DataHelper.makeWindTaServer;
import static org.capstone.DataHelper.makeWindTaServerWithClips;
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
        DiscordServer expected = makeWindTaServerWithClips();
        DiscordServer actual = discordServerRepository.findById(expected.getDiscordServerId());
        assertEquals(expected.getDiscordServerId(), actual.getDiscordServerId());
        assertEquals(expected.getServername(), actual.getServername());
        assertEquals(1, actual.getClips().size());
        assertEquals(expected.getClips().get(0), actual.getClips().get(0));

        DiscordServer invalid = discordServerRepository.findById(234124235L);
        assertNull(invalid);
    }

    @Test
    void shouldAdd() {
        DiscordServer discordServer = makeWindTaServer();
        discordServer.setDiscordServerId(discordServer.getDiscordServerId()+1);
        DiscordServer actual = discordServerRepository.add(discordServer);
        assertNotNull(actual);
        assertEquals(discordServer.getDiscordServerId(), actual.getDiscordServerId());
    }

    @Test
    void shouldUpdate() {
        DiscordServer discordServer = makeWindTaServer();
        discordServer.setServername("New");

        // Check if update was successful
        assertTrue(discordServerRepository.update(discordServer));

        // Double check server was updated
        DiscordServer actual = discordServerRepository.findById(discordServer.getDiscordServerId());
        assertEquals(discordServer.getServername(), actual.getServername());

        // Unhappy path
        discordServer.setDiscordServerId(-1);
        assertFalse(discordServerRepository.update(discordServer));
    }

    @Test
    void shouldDelete() {
        DiscordServer discordServer = makeWindTaServer();
        assertTrue(discordServerRepository.deleteById(discordServer.getDiscordServerId()));
        assertFalse(discordServerRepository.deleteById(discordServer.getDiscordServerId()));
    }

}