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

        assertEquals(expected, actual);
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
