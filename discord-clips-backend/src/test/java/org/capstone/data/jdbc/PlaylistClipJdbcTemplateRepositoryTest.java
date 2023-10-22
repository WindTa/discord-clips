package org.capstone.data.jdbc;

import org.capstone.data.KnownGoodState;
import org.capstone.models.PlaylistClip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

import static org.capstone.DataHelper.makeWindTaPlaylistClip;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PlaylistClipJdbcTemplateRepositoryTest {

    @Autowired
    PlaylistClipJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldAdd() {
        // Should throw error if playlist already has this clip
        PlaylistClip playlistClip = makeWindTaPlaylistClip();
        assertThrows(DuplicateKeyException.class, () -> repository.add(playlistClip));

        // Should throw error if clip does not exist
        playlistClip.getClip().setClipId(3);
        assertThrows(DataIntegrityViolationException.class, () -> repository.add(playlistClip));

        // Should throw error if playlist does not exist
        playlistClip.setPlaylistId(2);
        playlistClip.getClip().setClipId(2);
        assertThrows(DataIntegrityViolationException.class, () -> repository.add(playlistClip));

        // Success
        playlistClip.setPlaylistId(1);
        assertTrue(repository.add(playlistClip));
    }

    @Test
    void shouldDeleteByKey() {
        assertTrue(repository.deleteByKey(1, 1));
        assertFalse(repository.deleteByKey(1, 1));
    }

}