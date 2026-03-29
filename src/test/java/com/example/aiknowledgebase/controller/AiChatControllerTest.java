package com.example.aiknowledgebase.controller;

import com.example.aiknowledgebase.service.AiChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AiChatController.class)
class AiChatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AiChatService aiChatService;

    @Test
    void shouldReturnAnswer() throws Exception {
        when(aiChatService.ask("什么是RAG?"))
                .thenReturn("RAG 是检索增强生成技术。");

        mockMvc.perform(post("/api/ai/chat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"question":"什么是RAG?"}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.answer").value("RAG 是检索增强生成技术。"));
    }
}
