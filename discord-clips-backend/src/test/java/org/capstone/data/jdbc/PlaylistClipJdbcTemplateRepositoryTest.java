package org.capstone.data.jdbc;

import org.capstone.data.KnownGoodState;
import org.capstone.models.Clip;
import org.capstone.models.PlaylistClip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
/*
        PlaylistClip playlistClip = makePlaylistClip();
        assertTrue(repository.add(playlistClip));
*/
    }

    @Test
    void shouldUpdate() {
    }

    @Test
    void shouldDelete() {
    }

/*
    PlaylistClip makePlaylistClip(int playlistId, int clipId, int displayOrder) {
        PlaylistClip playlistClip = new PlaylistClip();
        playlistClip.setPlaylistId(1);
        playlistClip.setDisplayOrder(1);

        Clip clip = new Clip();
        clip.setClipId(1);
        clip.setClipName("My Clip");
        clip.setYoutubeId("fSKQRDq3RkM");
        clip.setStartTime(5);
    }
*/
}