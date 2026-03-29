package com.example.aiknowledgebase.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.aiknowledgebase.config.AiModelProperties;
import com.example.aiknowledgebase.config.AiPromptProperties;
import com.example.aiknowledgebase.entity.KnowledgeDocument;
import com.example.aiknowledgebase.mapper.KnowledgeDocumentMapper;
import com.example.aiknowledgebase.model.openai.OpenAiChatRequest;
import com.example.aiknowledgebase.model.openai.OpenAiChatResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AiChatService {

    private final RestTemplate restTemplate;
    private final AiPromptProperties aiPromptProperties;
    private final AiModelProperties aiModelProperties;
    private final KnowledgeDocumentMapper knowledgeDocumentMapper;

    public AiChatService(RestTemplate restTemplate,
                         AiPromptProperties aiPromptProperties,
                         AiModelProperties aiModelProperties,
                         KnowledgeDocumentMapper knowledgeDocumentMapper) {
        this.restTemplate = restTemplate;
        this.aiPromptProperties = aiPromptProperties;
        this.aiModelProperties = aiModelProperties;
        this.knowledgeDocumentMapper = knowledgeDocumentMapper;
    }

    public String ask(String question) {
        List<KnowledgeDocument> docs = knowledgeDocumentMapper.selectList(new LambdaQueryWrapper<KnowledgeDocument>()
                .like(KnowledgeDocument::getContent, question)
                .last("limit 3"));

        StringBuilder contextBuilder = new StringBuilder();
        for (KnowledgeDocument doc : docs) {
            contextBuilder.append("标题:").append(doc.getTitle()).append("\n")
                    .append("内容:").append(doc.getContent()).append("\n\n");
        }

        OpenAiChatRequest requestBody = new OpenAiChatRequest();
        requestBody.setModel(aiModelProperties.getModel());

        List<OpenAiChatRequest.Message> messages = new ArrayList<OpenAiChatRequest.Message>();
        messages.add(new OpenAiChatRequest.Message("system", aiPromptProperties.getSystemPrompt()));
        messages.add(new OpenAiChatRequest.Message("user",
                "已知知识库片段:\n" + contextBuilder + "请回答问题: " + question));
        requestBody.setMessages(messages);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (StringUtils.hasText(aiModelProperties.getApiKey())) {
            headers.setBearerAuth(aiModelProperties.getApiKey());
        }

        HttpEntity<OpenAiChatRequest> entity = new HttpEntity<OpenAiChatRequest>(requestBody, headers);
        OpenAiChatResponse response = restTemplate.postForObject(
                aiModelProperties.getBaseUrl() + "/v1/chat/completions",
                entity,
                OpenAiChatResponse.class);

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()
                || response.getChoices().get(0).getMessage() == null) {
            return "AI 服务暂无可用响应，请稍后重试。";
        }
        return response.getChoices().get(0).getMessage().getContent();
    }
}
