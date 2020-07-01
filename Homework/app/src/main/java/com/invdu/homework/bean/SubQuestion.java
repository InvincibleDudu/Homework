package com.invdu.homework.bean;

public class SubQuestion {
    private int id;
    private String comment;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SubQuestion(int id, String comment, String content) {
        this.id = id;
        this.comment = comment;
        this.content = content;
    }

    public SubQuestion(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
