package org.capstone.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.capstone.data.interfaces.DiscordServerRepository;
import org.capstone.models.DiscordServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DiscordServerControllerTest {

    @MockBean
    DiscordServerRepository discordServerRepository;

    @Autowired
    MockMvc mvc;

    @Test
    void getShouldReturn200() throws Exception {
        DiscordServer discordServer = makeDiscordServer();

        when(discordServerRepository.findById(1)).thenReturn(discordServer);
        ObjectMapper jsonMapper = new ObjectMapper();

        String discordServerJson = jsonMapper.writeValueAsString(discordServer);

        var request = get("/api/discord-servers/1");

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(discordServerJson));
    }

    @Test
    void getShouldReturn404WhenNotFound() throws Exception {
        when(discordServerRepository.findById(1)).thenReturn(null);
        var request = get("/api/discord-servers/1");
        mvc.perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    void addShouldReturn201() throws Exception {

        DiscordServer discordServer = makeDiscordServer();

        when(discordServerRepository.add(any())).thenReturn(discordServer);
        ObjectMapper jsonMapper = new ObjectMapper();

        String discordServerJson = jsonMapper.writeValueAsString(discordServer);

        var request = post("/api/discord-servers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(discordServerJson);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().json(discordServerJson));
    }

    @Test
    void addShouldReturn400WhenEmpty() throws Exception {
        var request = post("/api/discord-servers")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void addShouldReturn400WhenInvalid() throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();

        DiscordServer discordServer = new DiscordServer();
        String discordServerJson = jsonMapper.writeValueAsString(discordServer);

        var request = post("/api/discord-servers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(discordServerJson);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void addShouldReturn415WhenInvalid() throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();

        DiscordServer discordServer = makeDiscordServer();
        String discordServerJson = jsonMapper.writeValueAsString(discordServer);

        var request = post("/api/discord-servers")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .content(discordServerJson);

        mvc.perform(request)
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void updateShouldReturn204() throws Exception {

        DiscordServer discordServer = makeDiscordServer();

        when(discordServerRepository.update(any())).thenReturn(true);
        ObjectMapper jsonMapper = new ObjectMapper();

        String discordServerJson = jsonMapper.writeValueAsString(discordServer);

        var request = put("/api/discord-servers/"+discordServer.getDiscordServerId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(discordServerJson);

        mvc.perform(request)
                .andExpect(status().isNoContent());
    }

    @Test
    void updateShouldReturn400WhenEmpty() throws Exception {
        var request = put("/api/discord-servers/1")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateShouldReturn400WhenInvalid() throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();

        DiscordServer discordServer = new DiscordServer();
        discordServer.setDiscordServerId(1);
        String discordServerJson = jsonMapper.writeValueAsString(discordServer);

        var request = put("/api/discord-servers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(discordServerJson);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateShouldReturn404WhenNotFound() throws Exception {

        DiscordServer discordServer = makeDiscordServer();

        when(discordServerRepository.update(any())).thenReturn(false);
        ObjectMapper jsonMapper = new ObjectMapper();

        String discordServerJson = jsonMapper.writeValueAsString(discordServer);

        var request = put("/api/discord-servers/"+discordServer.getDiscordServerId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(discordServerJson);

        mvc.perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    void updateShouldReturn409WhenConflict() throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();

        DiscordServer discordServer = new DiscordServer();
        String discordServerJson = jsonMapper.writeValueAsString(discordServer);

        var request = put("/api/discord-servers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(discordServerJson);

        mvc.perform(request)
                .andExpect(status().isConflict());
    }

    @Test
    void updateShouldReturn415WhenInvalid() throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();

        DiscordServer discordServer = makeDiscordServer();
        String discordServerJson = jsonMapper.writeValueAsString(discordServer);

        var request = put("/api/discord-servers/1")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .content(discordServerJson);

        mvc.perform(request)
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void deleteShouldReturn204() throws Exception {
        when(discordServerRepository.deleteById(1)).thenReturn(true);
        var request = delete("/api/discord-servers/1");
        mvc.perform(request)
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteShouldReturn404WhenNotFound() throws Exception {
        when(discordServerRepository.deleteById(1)).thenReturn(false);
        var request = delete("/api/discord-servers/1");
        mvc.perform(request)
                .andExpect(status().isNotFound());
    }

    private DiscordServer makeDiscordServer() {
        DiscordServer discordServer = new DiscordServer();
        discordServer.setDiscordServerId(1161381438839607358L);
        discordServer.setServername("WindTa's server");

        return discordServer;
    }

}