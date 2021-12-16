package com.aca.db;

public class QuestionNotFoundException extends RuntimeException {
    private final Number questionId;

    public QuestionNotFoundException(final Number questionId) {
        super(String.format("User not found for id: %s", questionId));
        this.questionId = questionId;
    }
}
