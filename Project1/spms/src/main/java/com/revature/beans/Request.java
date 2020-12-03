package com.revature.beans;

import java.util.Objects;

public class Request {
    private Integer id;
    private Story story;
    private Editor sender;
    private Author toAuthor;
    private Editor toEditor;
    private String message;

    public Request() {
        id = 0;
        story = null;
        sender = null;
        toAuthor = null;
        toEditor = null;
        message = "";
    }

    public Request(Integer id, Story story, Editor sender, Author toAuthor, Editor toEditor, String message) {
        this.id = id;
        this.story = story;
        this.sender = sender;
        this.toAuthor = toAuthor;
        this.toEditor = toEditor;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public Editor getSender() {
        return sender;
    }

    public void setSender(Editor sender) {
        this.sender = sender;
    }

    public Author getToAuthor() {
        return toAuthor;
    }

    public void setToAuthor(Author toAuthor) {
        this.toAuthor = toAuthor;
    }

    public Editor getToEditor() {
        return toEditor;
    }

    public void setToEditor(Editor toEditor) {
        this.toEditor = toEditor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(id, request.id) && Objects.equals(story, request.story) && Objects.equals(sender, request.sender) && Objects.equals(toAuthor, request.toAuthor) && Objects.equals(toEditor, request.toEditor) && Objects.equals(message, request.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, story, sender, toAuthor, toEditor, message);
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", story=" + story +
                ", sender=" + sender +
                ", toAuthor=" + toAuthor +
                ", toEditor=" + toEditor +
                ", message='" + message + '\'' +
                '}';
    }
}
