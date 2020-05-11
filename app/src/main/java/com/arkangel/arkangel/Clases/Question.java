package com.arkangel.arkangel.Clases;

import androidx.annotation.NonNull;

public class Question {
    private String  id_question;
    private String  question;
    private int     real_test_pos;
    private String  personality_profile;
    private String  status;
    private String  value;

    public Question(String id_question, String question, int real_test_pos, String personality_profile, String status, String value) {
        this.id_question = id_question;
        this.question = question;
        this.real_test_pos = real_test_pos;
        this.personality_profile = personality_profile;
        this.status = status;
        this.value = value;
    }

    public String getId_question() {
        return id_question;
    }

    public String getQuestion() {
        return question;
    }

    public int getReal_test_pos() {
        return real_test_pos;
    }

    public String getPersonality_profile() {
        return personality_profile;
    }

    public String getStatus() {
        return status;
    }

    public String getValue() {
        return value;
    }

    public Question() {

    }

    @NonNull
    @Override
    public String toString() {
        return getQuestion()+getId_question();
    }
}
