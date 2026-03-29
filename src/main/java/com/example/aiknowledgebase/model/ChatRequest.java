package com.example.aiknowledgebase.model;

import jakarta.validation.constraints.NotBlank;

public record ChatRequest(
        @NotBlank(message = "question 不能为空")
        String question
) {
}
