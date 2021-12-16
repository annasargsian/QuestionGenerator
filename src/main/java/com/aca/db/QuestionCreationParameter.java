package com.aca.db;

public class QuestionCreationParameter {
    private final String text;
    private final String answer;

    public QuestionCreationParameter(String text, String answer) {
        this.text = text;
        this.answer = answer;
    }

    public String getText() {
        return text;
    }

    public String getAnswer() {
        return answer;
    }

}
