package com.example.cw_trivialpursuit;

import java.util.Vector;

public class Card {
    private String question;
    private String rightAnswer;
    private final Vector<String> wrongAnswers;

    public String getQuestion() {
        return question;
    }
    public String getRightAnswer() {
        return rightAnswer;
    }
    public Vector<String> getWrongAnswers() {
        return wrongAnswers;
    }
    public Card() {
        wrongAnswers = new Vector<>();
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
    public void addWrongAnswer(String wrongAnswer) {
        wrongAnswers.add(wrongAnswer);
    }
}
