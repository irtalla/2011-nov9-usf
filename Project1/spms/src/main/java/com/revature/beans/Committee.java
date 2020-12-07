package com.revature.beans;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class Committee {
    @Id
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genre")
    private Genre genre;

    public Committee() {
        id = 0;
        genre = null;
    }

    public Committee(Integer id, Genre genre) {
        this.id = id;
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Committee committee = (Committee) o;
        return Objects.equals(id, committee.id) && Objects.equals(genre, committee.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, genre);
    }

    @Override
    public String toString() {
        return "Committee{" +
                "id=" + id +
                ", genre=" + genre +
                '}';
    }
}
