package com.example.aiknowledgebase.model;

import javax.validation.constraints.NotBlank;

public class ChatRequest {

    @NotBlank(message = "question 不能为空")
    private String question;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
