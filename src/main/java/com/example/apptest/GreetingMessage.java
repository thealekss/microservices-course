package com.example.apptest;

public class GreetingMessage {

    private Long id;
    private String content;

    public GreetingMessage(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public GreetingMessage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "GreetingMessage{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
