package org.capstone.data.jdbc;

import org.capstone.data.KnownGoodState;
import org.capstone.models.DiscordServerClip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.capstone.DataHelper.makeWindTaDiscordServerClip;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class DiscordServerClipJdbcTemplateRepositoryTest {

    @Autowired
    DiscordServerClipJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldAdd() {
        DiscordServerClip discordServerClip = makeWindTaDiscordServerClip();
        discordServerClip.getClip().setClipId(2);
        assertTrue(repository.add(discordServerClip));
    }

    @Test
    void shouldDeleteByKey() {
        assertTrue(repository.deleteByKey(1161381438839607358L, 1));
        assertFalse(repository.deleteByKey(1161381438839607358L, 1));
    }
}