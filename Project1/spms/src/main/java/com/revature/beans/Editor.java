package com.revature.beans;

import java.util.Objects;

public class Editor {
    private Integer id;
    private String firstName;
    private String lastName;
    private EditorRole role;

    public Editor() {
        id = 0;
        firstName = "";
        lastName = "";
        role = null;
    }

    public Editor(Integer id, String firstName, String lastName, EditorRole role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public EditorRole getRole() { return role; }

    public void setRole(EditorRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Editor editor = (Editor) o;
        return Objects.equals(id, editor.id) && Objects.equals(firstName, editor.firstName) && Objects.equals(lastName, editor.lastName) && Objects.equals(role, editor.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, role);
    }

    @Override
    public String toString() {
        return "Editor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                '}';
    }
}
