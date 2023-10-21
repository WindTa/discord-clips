package org.capstone.domain;

import org.capstone.data.interfaces.DiscordUserRepository;
import org.capstone.models.DiscordUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DiscordUserServiceTest {
    @Autowired
    DiscordUserService discordUserService;

    @MockBean
    DiscordUserRepository discordUserRepository;

    @Test
    void shouldFindWindTa() {
        DiscordUser expected = makeDiscordUser();
        when(discordUserRepository.findById(221863292681977857L)).thenReturn(expected);
        DiscordUser actual = discordUserService.findById(221863292681977857L);
        assertEquals(expected, actual);
    }

    @Test
    void shouldAddWhenValid() {
        DiscordUser expected = makeDiscordUser();
        expected.setDiscordUserId(expected.getDiscordUserId()+1);

        when(discordUserRepository.add(expected)).thenReturn(expected);
        Result<DiscordUser> result = discordUserService.add(expected);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(expected, result.getPayload());
    }

    @Test
    void shouldNotAddWhenDiscordUserInvalid() {
        DiscordUser discordUser = null;
        Result<DiscordUser> result = discordUserService.add(discordUser);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("discord user cannot be null", result.getMessages().get(0));

        discordUser = makeDiscordUser();
        when(discordUserRepository.findById(discordUser.getDiscordUserId())).thenReturn(discordUser);
        result = discordUserService.add(discordUser);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("user id must be unique", result.getMessages().get(0));

        discordUser.setUsername(null);
        result = discordUserService.add(discordUser);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("username is required", result.getMessages().get(0));

        discordUser.setDiscordUserId(-1);
        result = discordUserService.add(discordUser);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("discordUserId must be >= 0", result.getMessages().get(0));

    }

    @Test
    void shouldUpdate() {
        DiscordUser discordUser = makeDiscordUser();
        discordUser.setUsername("New");

        when(discordUserRepository.update(discordUser)).thenReturn(true);
        Result<DiscordUser> actual = discordUserService.update(discordUser);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateMissing() {
        DiscordUser discordUser = makeDiscordUser();
        discordUser.setDiscordUserId(0);

        when(discordUserRepository.update(discordUser)).thenReturn(false);
        Result<DiscordUser> actual = discordUserService.update(discordUser);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

    @Test
    void shouldNotUpdateWhenInvalid() {
        DiscordUser discordUser = null;
        Result<DiscordUser> actual = discordUserService.update(discordUser);
        assertEquals(ResultType.INVALID, actual.getType());
        assertEquals("discord user cannot be null", actual.getMessages().get(0));

        discordUser = makeDiscordUser();
        discordUser.setUsername(null);
        actual = discordUserService.update(discordUser);
        assertEquals(ResultType.INVALID, actual.getType());
        assertEquals("username is required", actual.getMessages().get(0));
    }

    @Test
    void shouldDeleteWhenFound() {
        when(discordUserRepository.deleteById(1)).thenReturn(true);
        assertTrue(discordUserService.deleteById(1));
    }

    @Test
    void shouldDeleteWhenNotFound() {
        when(discordUserRepository.deleteById(1)).thenReturn(false);
        assertFalse(discordUserService.deleteById(1));
    }


    private DiscordUser makeDiscordUser() {
        DiscordUser discordUser = new DiscordUser();
        discordUser.setDiscordUserId(221863292681977857L);
        discordUser.setUsername("windta");

        return discordUser;
    }
}
