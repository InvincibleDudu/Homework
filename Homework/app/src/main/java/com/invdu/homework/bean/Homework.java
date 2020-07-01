package com.invdu.homework.bean;

public class Homework {
    private int id;
    private String title;
    private String chapter;
    private String description;
    private String answer;
    private int score;
    private boolean isSub;
    private int includedQuestions;


    public Homework(String chapter, String title, String description, int score, boolean isSub, int includedQuestions) {
        this.title = title;
        this.chapter = chapter;
        this.description = description;
        this.score = score;
        this.includedQuestions = includedQuestions;
        this.isSub = isSub;
    }

    public Homework(String chapter, String title, String description, String answer,  int score, boolean isSub, int includedQuestions) {
        this.title = title;
        this.chapter = chapter;
        this.score = score;
        this.answer = answer;
        this.description = description;
        this.includedQuestions = includedQuestions;
        this.isSub = isSub;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", chapter='" + chapter + '\'' +
                ", description='" + description + '\'' +
                ", answer='" + answer + '\'' +
                ", score=" + score +
                ", isSub=" + isSub +
                ", includedQuestions=" + includedQuestions +
                '}';
    }

    public Homework(int id, String chapter, String title, String description, String answer, int score, boolean isSub, int includedQuestions) {
        this.id = id;
        this.title = title;
        this.chapter = chapter;
        this.description = description;
        this.answer = answer;
        this.score = score;
        this.isSub = isSub;
        this.includedQuestions = includedQuestions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSub() {
        return isSub;
    }

    public void setSub(boolean sub) {
        isSub = sub;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIncludedQuestions() {
        return includedQuestions;
    }

    public void setIncludedQuestions(int includedQuestions) {
        this.includedQuestions = includedQuestions;
    }
}
