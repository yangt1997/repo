package com.example.aiknowledgebase;

import com.example.aiknowledgebase.config.AiPromptProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AiPromptProperties.class)
public class AiKnowledgeBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiKnowledgeBaseApplication.class, args);
    }
}
