package org.example.assignment1.exception;

public class AuthorNotFoundException extends Exception {
    private long author_id;
    public AuthorNotFoundException(long author_id) {
        super(String.format("Author with id %d does not exist", author_id));
    }
}
