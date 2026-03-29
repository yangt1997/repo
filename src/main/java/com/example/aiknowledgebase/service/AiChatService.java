package com.example.aiknowledgebase.service;

import com.example.aiknowledgebase.config.AiPromptProperties;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiChatService {

    private final ChatClient chatClient;
    private final AiPromptProperties aiPromptProperties;

    public AiChatService(ChatClient.Builder chatClientBuilder, AiPromptProperties aiPromptProperties) {
        this.chatClient = chatClientBuilder.build();
        this.aiPromptProperties = aiPromptProperties;
    }

    public String ask(String question) {
        return chatClient.prompt()
                .system(aiPromptProperties.systemPrompt())
                .user(question)
                .call()
                .content();
    }
}
