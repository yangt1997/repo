package com.example.aiknowledgebase.model.openai;

import java.util.List;

public class OpenAiChatResponse {

    private List<Choice> choices;

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public static class Choice {
        private OpenAiChatRequest.Message message;

        public OpenAiChatRequest.Message getMessage() {
            return message;
        }

        public void setMessage(OpenAiChatRequest.Message message) {
            this.message = message;
        }
    }
}
