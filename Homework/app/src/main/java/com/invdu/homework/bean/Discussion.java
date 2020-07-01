package com.invdu.homework.bean;

public class Discussion {
    private int id;
    private String message;
    private String time;
    private String username;

    public Discussion(int id, String message, String time, String username) {
        this.id = id;
        this.message = message;
        this.time = time;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Discussion{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", time='" + time + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
