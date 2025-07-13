package org.example.assignment1.exception;

public class BookNotFoundException extends Exception {
    private long book_id;
    public BookNotFoundException(long book_id) {
        super(String.format("Book with id %d does not exist", book_id));
    }
}
