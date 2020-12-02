package com.revature.beans;

import java.util.Date;
import java.util.Objects;

public class Story {
    private Integer id;
    private Author author;
    private String title;
    private Date completionDate;
    private StoryType type;
    private Genre genre;
    private String tagline;
    private String description;
    private String text;
    private Status status;
    private Committee committee;

    public Story() {
        id = 0;
        author = null;
        title = "";
        completionDate = null;
        type = null;
        genre = null;
        tagline = "";
        description = "";
        text = "";
        status = null;
        committee = null;
    }

    public Story(Integer id, Author author, String title, Date completionDate, StoryType type, Genre genre, String tagline, String description, String text, Status status, Committee committee) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.completionDate = completionDate;
        this.type = type;
        this.genre = genre;
        this.tagline = tagline;
        this.description = description;
        this.text = text;
        this.status = status;
        this.committee = committee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public StoryType getType() {
        return type;
    }

    public void setType(StoryType type) {
        this.type = type;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Committee getCommittee() {
        return committee;
    }

    public void setCommittee(Committee committee) {
        this.committee = committee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Story story = (Story) o;
        return Objects.equals(id, story.id) && Objects.equals(author, story.author) && Objects.equals(title, story.title) && Objects.equals(completionDate, story.completionDate) && Objects.equals(type, story.type) && Objects.equals(genre, story.genre) && Objects.equals(tagline, story.tagline) && Objects.equals(description, story.description) && Objects.equals(text, story.text) && Objects.equals(status, story.status) && Objects.equals(committee, story.committee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, completionDate, type, genre, tagline, description, text, status, committee);
    }

    @Override
    public String toString() {
        return "Story{" +
                "id=" + id +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", completionDate=" + completionDate +
                ", type=" + type +
                ", genre=" + genre +
                ", tagline='" + tagline + '\'' +
                ", description='" + description + '\'' +
                ", text='" + text + '\'' +
                ", status=" + status +
                ", committee=" + committee +
                '}';
    }
}
