package org.example.assignment1.controller;

import org.example.assignment1.exception.AuthorNotFoundException;
import org.example.assignment1.exception.BookNotFoundException;
import org.example.assignment1.model.Author;
import org.example.assignment1.model.Book;
import org.example.assignment1.repository.AuthorRepository;
import org.example.assignment1.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@Controller
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;


    // Get All Books
    @GetMapping({"/", "/books"})
    public String getAllBooks(Model model) {
        List<Book> listBooks = bookRepository.findAll();
        model.addAttribute("listBooks", listBooks);
        return "index";

    }


    @RequestMapping("/new")
    public String createBook(Model model){
        model.addAttribute("book", new Book());
        return "addBook";
    }

    // Create a new Book
    @PostMapping("/books")
    public String newBook(@ModelAttribute("book") Book book, Model model)
    {
        bookRepository.save(book);
        return "redirect:/books";
    }



    // Get a Single Book
    @GetMapping("/books/{id}")
    public String getBookById(@PathVariable(value = "id") Long bookId, Model model) throws BookNotFoundException {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
        model.addAttribute("book", book);
        List<Author> authors = authorRepository.findAll();

        List<Author> missingAuthors = new ArrayList<Author>();
        List<Author> currentAuthors = new ArrayList<Author>();
        for(Author author : authors){
            if(!book.getAuthors().contains(author))
                missingAuthors.add(author);

        }
        model.addAttribute("missingAuthors", missingAuthors);
        model.addAttribute("authors", book.getAuthors());
        return "editBook";

    }

    // Update an Existing Book
    @PutMapping("/save")
    public String updateBook(@ModelAttribute("book") Book book, Model model)
            throws BookNotFoundException{
        Book bookRep = bookRepository.findById(book.getId()).orElseThrow(() -> new BookNotFoundException(book.getId()));
        bookRep.setBook_name(book.getBook_name());
        bookRep.setIsbn(book.getIsbn());
        bookRepository.save(bookRep);
        return "redirect:/books/"+book.getId();
    }

    // Delete a Book
    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable(value = "id") Long bookId, Model model) throws BookNotFoundException {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
        bookRepository.delete(book);
        return "redirect:/books";
    }

    // Delete a Note
    @DeleteMapping("/removeAuthor/{id}")
    public String deleteAuthorship(@PathVariable(value = "id") Long bookId, @RequestParam("id") Long authorId,  Model model) throws  BookNotFoundException, AuthorNotFoundException {
        System.out.println("Delete Authorship");
        Book book = bookRepository.findById(bookId).orElseThrow();
        Author author = authorRepository.findById(authorId).orElseThrow();
        System.out.println(author.getId()+" "+author.getAuthorName()+ " "+author.getAuthorSurname());
        book.getAuthors().remove(author);
        bookRepository.save(book);

        return "redirect:/books/" + String.valueOf(bookId) ;

    }


    @PutMapping("/addAuthor/{id}")
    public String addAuthor(@PathVariable(value = "id") Long bookId, @RequestParam("authorMissing") Long authorId,  Model model) throws  BookNotFoundException, AuthorNotFoundException {
        System.out.println("Add Authorship");
        Book book = bookRepository.findById(bookId).orElseThrow();
        Author author = authorRepository.findById(authorId).orElseThrow();
        book.getAuthors().add(author);
        System.out.println(author.getId()+" "+author.getAuthorName()+ " "+author.getAuthorSurname());
        bookRepository.save(book);
        return "redirect:/books/" + String.valueOf(bookId) ;
    }



}

