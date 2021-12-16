package com.aca.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Question {
    private Long id;
    private String text;
    private String answer;
    private LocalDateTime created_On;
    private LocalDateTime updated_On;


    public Question(Long id, String text, String answer) {
        this.id = id;
        this.text = text;
        this.answer = answer;
        this.created_On = LocalDateTime.now();
        this.updated_On = null;

    }


    public Question(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getLong("id");
        this.text = resultSet.getString("text");
        this.answer = resultSet.getString("answer");
        this.created_On = resultSet.getTimestamp("created_on").toLocalDateTime();

        Timestamp update_On = resultSet.getTimestamp("updated_on");
        if (update_On != null) {
            this.updated_On = update_On.toLocalDateTime();
        }

    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", answer='" + answer + '\'' +
                ", created_On=" + created_On +
                ", updated_On=" + updated_On +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public LocalDateTime getCreated_On() {
        return created_On;
    }

    public void setCreated_On(LocalDateTime created_On) {
        this.created_On = created_On;
    }

    public LocalDateTime getUpdated_On() {
        return updated_On;
    }

    public void setUpdated_On(LocalDateTime updated_On) {
        this.updated_On = updated_On;
    }

}
