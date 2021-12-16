package com.aca.db;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
//           HashMapImpl hashMap = new HashMapImpl();
//           hashMap.create(new QuestionCreationParameter("Is String immutable class?","Yes"));
//           hashMap.create(new QuestionCreationParameter("Who wrote Hamlet?","Shakespeare"));
//           hashMap.create(new QuestionCreationParameter("How old is Super Mario?","24"));
//
//           System.out.println(hashMap.search("Who wrote Hamlet?", "24"));
//
//           hashMap.update(1l,new QuestionCreationParameter("Is String immutable class?","Yes"));
//           hashMap.update(2l,new QuestionCreationParameter("Who wrote Hamlet?","Shakespeare"));
//           hashMap.update(3l,new QuestionCreationParameter("How old is Super Mario?","24"));
//
//           System.out.println(hashMap.findById(1l));
//           System.out.println(hashMap.getById(2l));

        DataBaseImpl dataBase = new DataBaseImpl();
        dataBase.create(new QuestionCreationParameter("Is String immutable class?", "Yes"));
        dataBase.create(new QuestionCreationParameter("Who wrote Hamlet?", "Shakespeare"));
        dataBase.create(new QuestionCreationParameter("How old is Super Mario?", "24"));

        System.out.println(dataBase.search("Who wrote Hamlet?", "24"));

        dataBase.update(1l, new QuestionCreationParameter("Is String immutable class?", "Yes"));
        dataBase.update(2l, new QuestionCreationParameter("Who wrote Hamlet?", "Shakespeare"));
        dataBase.update(3l, new QuestionCreationParameter("How old is Super Mario?", "24"));

        System.out.println(dataBase.findById(1l));
        System.out.println(dataBase.getById(2l));
    }
}
