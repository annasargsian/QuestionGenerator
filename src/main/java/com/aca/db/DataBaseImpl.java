package com.aca.db;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class DataBaseImpl implements QuestionService {

    private static final String CREATE_SQL =
            "insert into questions (id,text,answer,created_On)\n" +
                    "values(?,?,?,?)";

    private static final String FIND_BY_ID_SQL = "select * from questions where id = ?";

    private static final String GET_MAX_ID_SQL = "select max(id) from questions";

    private static final String UPDATE_QUESTION_SQL = "update questions set text = ?, answer = ? where id = ?";

    private static final String SEARCH_SQL = "select * from questions where text = ? or answer = ?";


    private final AtomicLong idGenerator;

    public DataBaseImpl() throws SQLException {
        this.idGenerator = new AtomicLong(getMaxID());
    }


    @Override
    public Long create(QuestionCreationParameter q) throws SQLException {
        String URL = "jdbc:postgresql://localhost/postgres";
        String USER = "postgres";
        String PASSWORD = "123321qweewq";
        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.prepareStatement(CREATE_SQL);

            Long generatedId = idGenerator.incrementAndGet();
            statement.setLong(1, generatedId);
            statement.setString(2, q.getText());
            statement.setString(3, q.getAnswer());
            statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));

            statement.execute();
            return generatedId;

        } catch (SQLException ex) {
            throw new IllegalStateException("Created question failed. " + ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public Question getById(Long id) throws SQLException {
        return findById(id).orElseThrow(() -> new QuestionNotFoundException(id));
    }

    @Override
    public Optional<Question> findById(Long id) throws SQLException {
        String URL = "jdbc:postgresql://localhost/postgres";
        String USER = "postgres";
        String PASSWORD = "123321qweewq";
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            statement = connection.prepareStatement(FIND_BY_ID_SQL);
            statement.setLong(1, id);

            resultSet = statement.executeQuery();

            return resultSet.next()
                    ? Optional.of(new Question((resultSet)))
                    : Optional.empty();
        } catch (SQLException ex) {
            throw new IllegalStateException("Find question by id failed. " + ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public ArrayList<Question> search(String text, String answer) throws SQLException {
        String URL = "jdbc:postgresql://localhost/postgres";
        String USER = "postgres";
        String PASSWORD = "123321qweewq";
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        ArrayList<Question> questions = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            statement = connection.prepareStatement(SEARCH_SQL);
            statement.setString(1, text);
            statement.setString(2, answer);

            resultSet = statement.executeQuery();


            while (resultSet.next()) {
                if (questions.size() == 2) {
                    break;
                }
                questions.add(new Question(resultSet));
            }
            return questions;


        } catch (SQLException ex) {
            throw new IllegalStateException("Find question by text and answer failed. " + ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public boolean update(Long id, QuestionCreationParameter questionCreationParameter) throws SQLException {
        String URL = "jdbc:postgresql://localhost/postgres";
        String USER = "postgres";
        String PASSWORD = "123321qweewq";
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.prepareStatement(UPDATE_QUESTION_SQL);

            statement.setString(1, questionCreationParameter.getText());
            statement.setString(2, questionCreationParameter.getAnswer());
            statement.setLong(3, id);

            return !statement.execute();

        } catch (SQLException ex) {
            throw new IllegalStateException("Update question by id failed. " + ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private Long getMaxID() throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        String URL = "jdbc:postgresql://localhost/postgres";
        String USER = "postgres";
        String PASSWORD = "123321qweewq";
        Connection connection = null;
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        statement = connection.createStatement();

        resultSet = statement.executeQuery(GET_MAX_ID_SQL);

        return resultSet.next()
                ? resultSet.getLong(1)
                : 0L;
    }

}
