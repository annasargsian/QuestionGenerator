package com.aca.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface QuestionService {
    Long create(QuestionCreationParameter q) throws SQLException;

    Question getById(Long id) throws SQLException;

    Optional<Question> findById(Long id) throws SQLException;

    ArrayList<Question> search(String text, String answer) throws SQLException;

    boolean update(Long id, QuestionCreationParameter questionCreationParameter) throws SQLException;
}
