package com.example.aiknowledgebase.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.ai")
public record AiPromptProperties(String systemPrompt) {
}
