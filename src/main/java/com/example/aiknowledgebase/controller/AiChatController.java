package com.example.aiknowledgebase.controller;

import com.example.aiknowledgebase.model.ChatRequest;
import com.example.aiknowledgebase.model.ChatResponse;
import com.example.aiknowledgebase.service.AiChatService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class AiChatController {

    private final AiChatService aiChatService;

    public AiChatController(AiChatService aiChatService) {
        this.aiChatService = aiChatService;
    }

    @PostMapping("/chat")
    public ChatResponse chat(@Valid @RequestBody ChatRequest request) {
        String answer = aiChatService.ask(request.question());
        return new ChatResponse(answer);
    }
}
