package org.capstone.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.capstone.data.interfaces.DiscordUserRepository;
import org.capstone.models.DiscordUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DiscordUserControllerTest {

    @MockBean
    DiscordUserRepository discordUserRepository;

    @Autowired
    MockMvc mvc;

    @Test
    void getShouldReturn200() throws Exception {
        DiscordUser discordUser = makeDiscordUser();

        when(discordUserRepository.findById(1)).thenReturn(discordUser);
        ObjectMapper jsonMapper = new ObjectMapper();

        String discordUserJson = jsonMapper.writeValueAsString(discordUser);

        var request = get("/api/discord-users/1");

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(discordUserJson));
    }

    @Test
    void getShouldReturn404WhenNotFound() throws Exception {
        when(discordUserRepository.findById(1)).thenReturn(null);
        var request = get("/api/discord-users/1");
        mvc.perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    void addShouldReturn201() throws Exception {

        DiscordUser discordUser = makeDiscordUser();

        when(discordUserRepository.add(any())).thenReturn(discordUser);
        ObjectMapper jsonMapper = new ObjectMapper();

        String discordUserJson = jsonMapper.writeValueAsString(discordUser);

        var request = post("/api/discord-users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(discordUserJson);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().json(discordUserJson));
    }

    @Test
    void addShouldReturn400WhenEmpty() throws Exception {
        var request = post("/api/discord-users")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void addShouldReturn400WhenInvalid() throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();

        DiscordUser discordUser = new DiscordUser();
        String discordUserJson = jsonMapper.writeValueAsString(discordUser);

        var request = post("/api/discord-users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(discordUserJson);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void addShouldReturn415WhenInvalid() throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();

        DiscordUser discordUser = makeDiscordUser();
        String discordUserJson = jsonMapper.writeValueAsString(discordUser);

        var request = post("/api/discord-users")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .content(discordUserJson);

        mvc.perform(request)
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void updateShouldReturn204() throws Exception {

        DiscordUser discordUser = makeDiscordUser();

        when(discordUserRepository.update(any())).thenReturn(true);
        ObjectMapper jsonMapper = new ObjectMapper();

        String discordUserJson = jsonMapper.writeValueAsString(discordUser);

        var request = put("/api/discord-users/"+discordUser.getDiscordUserId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(discordUserJson);

        mvc.perform(request)
                .andExpect(status().isNoContent());
    }

    @Test
    void updateShouldReturn400WhenEmpty() throws Exception {
        var request = put("/api/discord-users/1")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateShouldReturn400WhenInvalid() throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();

        DiscordUser discordUser = new DiscordUser();
        discordUser.setDiscordUserId(1);
        String discordUserJson = jsonMapper.writeValueAsString(discordUser);

        var request = put("/api/discord-users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(discordUserJson);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateShouldReturn404WhenNotFound() throws Exception {

        DiscordUser discordUser = makeDiscordUser();

        when(discordUserRepository.update(any())).thenReturn(false);
        ObjectMapper jsonMapper = new ObjectMapper();

        String discordUserJson = jsonMapper.writeValueAsString(discordUser);

        var request = put("/api/discord-users/"+discordUser.getDiscordUserId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(discordUserJson);

        mvc.perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    void updateShouldReturn409WhenConflict() throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();

        DiscordUser discordUser = new DiscordUser();
        String discordUserJson = jsonMapper.writeValueAsString(discordUser);

        var request = put("/api/discord-users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(discordUserJson);

        mvc.perform(request)
                .andExpect(status().isConflict());
    }

    @Test
    void updateShouldReturn415WhenInvalid() throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();

        DiscordUser discordUser = makeDiscordUser();
        String discordUserJson = jsonMapper.writeValueAsString(discordUser);

        var request = put("/api/discord-users/1")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .content(discordUserJson);

        mvc.perform(request)
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void deleteShouldReturn204() throws Exception {
        when(discordUserRepository.deleteById(1)).thenReturn(true);
        var request = delete("/api/discord-users/1");
        mvc.perform(request)
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteShouldReturn404WhenNotFound() throws Exception {
        when(discordUserRepository.deleteById(1)).thenReturn(false);
        var request = delete("/api/discord-users/1");
        mvc.perform(request)
                .andExpect(status().isNotFound());
    }

    private DiscordUser makeDiscordUser() {
        DiscordUser discordUser = new DiscordUser();
        discordUser.setDiscordUserId(221863292681977857L);
        discordUser.setUsername("windta");

        return discordUser;
    }

}