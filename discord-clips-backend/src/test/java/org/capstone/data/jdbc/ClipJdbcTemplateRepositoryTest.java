package org.capstone.data.jdbc;

import org.capstone.data.KnownGoodState;
import org.capstone.data.jdbc.ClipJdbcTemplateRepository;
import org.capstone.models.Clip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ClipJdbcTemplateRepositoryTest {
    private static int NEXT_ID = 3;

    @Autowired
    ClipJdbcTemplateRepository clipJdbcTemplateRepository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void findByDiscordUserId() {
        List<Clip> expected = List.of(
                makeWindTaClip()
        );

        long discordUserId = 221863292681977857L;
        List<Clip> actual = clipJdbcTemplateRepository.findByDiscordUserId(discordUserId);
        assertEquals(1, actual.size());
        assertIterableEquals(expected, actual);

        actual = clipJdbcTemplateRepository.findByDiscordUserId(234541);
        assertEquals(0, actual.size());
    }

    @Test
    void findById() {
        Clip expected = makeWindTaClip();
        Clip actual = clipJdbcTemplateRepository.findById(1);
        expected.setPlaylists(actual.getPlaylists());

        assertEquals(expected, actual);
        assertEquals(1, actual.getPlaylists().size());
        assertEquals(1, actual.getPlaylists().get(0).getDisplayOrder());
    }

    @Test
    void shouldAdd() {
        Clip clip = makeWindTaClip();
        clip.setClipName("New Clip");

        Clip actual = clipJdbcTemplateRepository.add(clip);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getClipId());
    }

    @Test
    void shouldUpdate() {
        Clip clip = makeWindTaClip();
        clip.setClipName("Updated Clip");

        // Check if update was success
        assertTrue(clipJdbcTemplateRepository.update(clip));

        // Double check clip was updated
        Clip actual = clipJdbcTemplateRepository.findById(clip.getClipId());
        assertEquals(clip.getClipName(), actual.getClipName());

        // Unhappy path
        clip.setClipId(-1);
        assertFalse(clipJdbcTemplateRepository.update(clip));
    }

    @Test
    void shouldDelete() {
        assertTrue(clipJdbcTemplateRepository.deleteById(1));
        assertFalse(clipJdbcTemplateRepository.deleteById(1));
    }

    private Clip makeWindTaClip() {
        return makeClip(1, "My Clip", "fSKQRDq3RkM",
                5, 10, 1, 1,
                221863292681977857L);
    }

    private Clip makeClip(
            int clipId, String clipName, String youtubeId,
            double startTime, double endTime,
            double volume, double playbackSpeed,
            long discordUserId) {
        Clip clip = new Clip();
        clip.setClipId(clipId);
        clip.setClipName(clipName);
        clip.setYoutubeId(youtubeId);
        clip.setStartTime(startTime);
        clip.setEndTime(endTime);
        clip.setVolume(volume);
        clip.setPlaybackSpeed(playbackSpeed);
        clip.setDiscordUserId(discordUserId);

        return clip;
    }
}
