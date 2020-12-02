package com.revature.beans;

import java.util.Objects;

public class Author {
    private Integer id;
    private Integer user;
    private String firstName;
    private String lastName;
    private Integer points;

    public Author() {
        id = 0;
        user = 0;
        firstName = "";
        lastName = "";
        points = 0;
    }

    public Author(Integer id, Integer user, String firstName, String lastName, Integer points) {
        this.id = id;
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.points = points;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id) && Objects.equals(user, author.user) && Objects.equals(firstName, author.firstName) && Objects.equals(lastName, author.lastName) && Objects.equals(points, author.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, firstName, lastName, points);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", user=" + user +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", points=" + points +
                '}';
    }
}
