package com.aca.db;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HashMapImpl implements QuestionService {

    private final Map<Long, Question> storage = new HashMap<>();
    private Long key = 0l;

    @Override
    public Long create(QuestionCreationParameter questionCreationParameter) {
        Question question = new Question(key, questionCreationParameter.getText(), questionCreationParameter.getAnswer());
        storage.put(key, question);
        this.key++;
        return question.getId();
    }

    public boolean update(Long id, QuestionCreationParameter questionCreationParameter) {
        Question question;
        if ((question = getById(id)) != null) {
            question.setText(questionCreationParameter.getText());
            question.setAnswer(questionCreationParameter.getAnswer());
            question.setUpdated_On(LocalDateTime.now());
            return true;
        }
        return false;
    }

    @Override
    public Optional<Question> findById(Long id) {
        if (getById(id) != null) {
            return Optional.of(getById(id));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Question getById(Long id) {
        return storage.get(id);
    }

    @Override
    public ArrayList<Question> search(String text, String answer) {
        Question question;
        ArrayList<Question> result = new ArrayList<>();
        for (int i = 0; i < storage.size(); i++) {
            if ((question = storage.get(i)).getText().equals(text) || (question = storage.get(i)).getAnswer().equals(answer)) {
                result.add(storage.get(i));
            }
        }
        return result;
    }

}
