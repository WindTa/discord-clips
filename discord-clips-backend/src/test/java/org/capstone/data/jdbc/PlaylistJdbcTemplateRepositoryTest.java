package org.capstone.data.jdbc;

import org.capstone.data.KnownGoodState;
import org.capstone.models.Playlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.capstone.DataHelper.makeWindTasPlaylist;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PlaylistJdbcTemplateRepositoryTest {

    final static int NEXT_ID = 2;

    @Autowired
    PlaylistJdbcTemplateRepository playlistJdbcTemplateRepository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void findByDiscordUserId() {
        List<Playlist> expected = List.of(
                makeWindTasPlaylist()
        );

        long discordUserId = 221863292681977857L;
        List<Playlist> actual = playlistJdbcTemplateRepository.findByDiscordUserId(discordUserId);
        assertEquals(1, actual.size());
        assertIterableEquals(expected, actual);

        actual = playlistJdbcTemplateRepository.findByDiscordUserId(0);
        assertEquals(0, actual.size());
    }

    @Test
    void findById() {
        Playlist expected = makeWindTasPlaylist();
        Playlist actual = playlistJdbcTemplateRepository.findById(1);
        expected.setClips(actual.getClips());

        assertEquals(expected, actual);
        assertEquals(1, actual.getClips().size());
        assertEquals(1, actual.getClips().get(0).getDisplayOrder());
    }

    @Test
    void shouldAdd() {
        Playlist playlist = makeWindTasPlaylist();
        playlist.setPlaylistName("Playlist 2");

        Playlist actual = playlistJdbcTemplateRepository.add(playlist);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getPlaylistId());
    }

    @Test
    void shouldUpdate() {
        Playlist playlist = makeWindTasPlaylist();
        playlist.setPlaylistName("Updated Playlist");

        // Check if update was successful
        assertTrue(playlistJdbcTemplateRepository.update(playlist));

        // Double check playlist was updated
        Playlist actual = playlistJdbcTemplateRepository.findById(playlist.getPlaylistId());
        assertEquals(playlist.getPlaylistName(), actual.getPlaylistName());

        // Unhappy path
        playlist.setPlaylistId(-1);
        assertFalse(playlistJdbcTemplateRepository.update(playlist));
    }

    @Test
    void shouldDelete() {
        assertTrue(playlistJdbcTemplateRepository.deleteById(1));
        assertFalse(playlistJdbcTemplateRepository.deleteById(1));
    }

}
