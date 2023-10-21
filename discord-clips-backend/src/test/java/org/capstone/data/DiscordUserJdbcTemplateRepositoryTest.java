package org.capstone.data;

import org.capstone.data.jdbc.DiscordUserJdbcTemplateRepository;
import org.capstone.models.DiscordUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class DiscordUserJdbcTemplateRepositoryTest {

    @Autowired
    DiscordUserJdbcTemplateRepository discordUserRepository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void findById() {
        DiscordUser windta = discordUserRepository.findById(221863292681977857L);
        assertEquals(221863292681977857L, windta.getDiscordUserId());
        assertEquals("windta", windta.getUsername());

        DiscordUser invalid = discordUserRepository.findById(234124235L);
        assertNull(invalid);
    }

    @Test
    void shouldAdd() {
        DiscordUser discordUser = makeDiscordUser();
        discordUser.setDiscordUserId(discordUser.getDiscordUserId()+1);
        DiscordUser actual = discordUserRepository.add(discordUser);
        assertNotNull(actual);
        assertEquals(discordUser.getDiscordUserId(), actual.getDiscordUserId());
    }

    @Test
    void shouldUpdate() {
        DiscordUser discordUser = makeDiscordUser();
        discordUser.setUsername("New");

        // Check if update was successful
        assertTrue(discordUserRepository.update(discordUser));

        // Double check user was updated
        DiscordUser actual = discordUserRepository.findById(discordUser.getDiscordUserId());
        assertEquals(discordUser, actual);

        // Unhappy path
        discordUser.setDiscordUserId(0);
        assertFalse(discordUserRepository.update(discordUser));
    }

    @Test
    void shouldDelete() {
        assertTrue(discordUserRepository.deleteById(221863292681977857L));
        assertFalse(discordUserRepository.deleteById(221863292681977857L));
    }

    private DiscordUser makeDiscordUser() {
        DiscordUser discordUser = new DiscordUser();
        discordUser.setDiscordUserId(221863292681977857L);
        discordUser.setUsername("windta");

        return discordUser;
    }
}