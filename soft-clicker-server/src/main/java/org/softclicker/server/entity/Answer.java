package org.softclicker.server.entity;

import java.util.Date;

/**
 * This class acts as the Answer entity and intends to use as an immutable object.
 * You can create new {@link Answer} only by passing a parameters through the constructor.
 */
public class Answer {

    public enum ANSWERS{
        A,B,C,D,E
    }

    private int answerId;
    final private String answer;
    final private Question question;
    final private User owner;
    final private Date answeredTime;

    public Answer(int answerId, String answer, Question question, User owner, Date answeredTime) {
        this.answerId = answerId;
        this.answer = answer;
        this.question = question;
        this.owner = owner;
        this.answeredTime = (Date)answeredTime.clone();
    }

    public int getAnswerId() {
        return answerId;
    }

    public String getAnswer() {
        return answer;
    }

    public Question getQuestion() {
        return question;
    }

    public User getOwner() {
        return owner;
    }

    public Date getAnsweredTime() {
        return (Date)answeredTime.clone();
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    @Override
    public String toString() {
        return "Answer ID: " + answerId + "\tAnswer: " + answer + "\tCreated By: " + owner.getUserName()
                + "\tCreated On: " + answeredTime.toString();
    }
}
