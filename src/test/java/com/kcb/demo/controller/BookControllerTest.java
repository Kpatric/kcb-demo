package com.kcb.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcb.demo.dto.BookRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired private ObjectMapper om;
    @Autowired private org.springframework.test.web.servlet.MockMvc mvc;

    @Test void create_and_get() throws Exception {
        var payload = om.writeValueAsString(new BookRequest("DDD","Evans",2004));
        mvc.perform(post("/api/books").with(SecurityMockMvcRequestPostProcessors.httpBasic("user","password"))
                        .contentType(MediaType.APPLICATION_JSON).content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());

        mvc.perform(get("/api/books").with(SecurityMockMvcRequestPostProcessors.httpBasic("user","password")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").exists());
    }

    @Test void validation_error() throws Exception {
        var bad = om.writeValueAsString(new BookRequest("", "", 1200));
        mvc.perform(post("/api/books").with(SecurityMockMvcRequestPostProcessors.httpBasic("user","password"))
                        .contentType(MediaType.APPLICATION_JSON).content(bad))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"));
    }

    @Test void requires_auth() throws Exception {
        mvc.perform(get("/api/books"))
                .andExpect(status().isUnauthorized());
    }
}
