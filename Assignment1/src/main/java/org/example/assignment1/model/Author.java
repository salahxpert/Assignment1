package org.example.assignment1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name= "authors")
public class Author {

    @Id
    @GeneratedValue
    private Long author_id;

    @NotBlank
    private String authorName;

    @NotBlank
    private String authorSurname;

    @ManyToMany
    private List<Book> books;

    public Author() {
    }

    public Long getId() {
        return author_id;
    }
    public void setId(Long id) {
        this.author_id = id;
    }

    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public String getAuthorSurname() {
        return authorSurname;
    }
    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }


}
