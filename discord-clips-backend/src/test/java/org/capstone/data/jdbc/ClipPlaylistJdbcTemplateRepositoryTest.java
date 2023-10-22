package org.capstone.data.jdbc;

import org.capstone.data.KnownGoodState;
import org.capstone.models.ClipPlaylist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

import static org.capstone.DataHelper.makeWindTaClipPlaylist;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ClipPlaylistJdbcTemplateRepositoryTest {

    @Autowired
    ClipPlaylistJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldAdd() {
        ClipPlaylist clipPlaylist = makeWindTaClipPlaylist();

        // Should throw error if clip already belongs to playlist
        assertThrows(DuplicateKeyException.class, () -> repository.add(clipPlaylist));

        // Should throw error if playlist does not exist
        clipPlaylist.getPlaylist().setPlaylistId(2);
        assertThrows(DataIntegrityViolationException.class, () -> repository.add(clipPlaylist));

        // Should throw error if clip does not exist
        clipPlaylist.getPlaylist().setPlaylistId(1);
        clipPlaylist.setClipId(3);
        assertThrows(DataIntegrityViolationException.class, () -> repository.add(clipPlaylist));

        // Success
        clipPlaylist.setClipId(2);
        assertTrue(repository.add(clipPlaylist));
    }

    @Test
    void shouldDeleteByKey() {
        assertTrue(repository.deleteByKey(1, 1));
        assertFalse(repository.deleteByKey(1, 1));
    }
}