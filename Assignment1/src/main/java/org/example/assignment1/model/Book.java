package org.example.assignment1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "books")
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String book_name;

    @ManyToMany
    @JoinTable(
            name = "authorship",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;


    @NotBlank
    private String isbn;

    public Book() {
        super();
    }
    public Book(Long id, String book_name, String author_name, String isbn) {
        this.id = id;
        this.book_name = book_name;
        this.isbn = isbn;
        authors = new ArrayList<Author>();
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public List<Author> getAuthors() {
        return authors;
    }
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

}
