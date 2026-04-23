package com.askattest.interview.models;

import java.util.List;

public class Survey {
    public int id;
    public String name;
    public List<Question> questions;

    @Override
    public String toString() {
        return String.format("Survey ID: %d | Name: %s | Question Count: %d", id, name, questions.size());
    }

    public List<Integer> question_ids() {
        return questions.stream().map(question -> question.id).toList();
    }

    public Question questionById(int questionId) {
        return questions.stream().filter(question -> question.id == questionId).findFirst().get();
    }
}
