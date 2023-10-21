package org.capstone.data.jdbc;

import org.capstone.data.KnownGoodState;
import org.capstone.models.Playlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PlaylistJdbcTemplateRepositoryTest {

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

    private Playlist makeWindTasPlaylist() {
        return makePlaylist(1, "My Playlist", 221863292681977857L);
    }

    private Playlist makePlaylist(int playlistId, String playlistName, long discordUserId) {
        Playlist playlist = new Playlist();
        playlist.setPlaylistId(playlistId);
        playlist.setPlaylistName(playlistName);
        playlist.setDiscordUserId(discordUserId);

        return playlist;
    }
}
