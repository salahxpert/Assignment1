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

import java.util.List;

@Controller
public class AuthorController {
    @Autowired
    AuthorRepository authorRepository;

    // Get All Books
    @GetMapping( "/authors")
    public String getAllAuthors(Model model) {
        List<Author> listAuthors = authorRepository.findAll();
        model.addAttribute("listAuthors", listAuthors);
        return "authors";

    }

    @RequestMapping("/new_author")
    public String createAuthor(Model model){
        model.addAttribute("author", new Author());
        return "addAuthor";
    }

    // Create a new Book
    @PostMapping("/authors")
    public String newAuthor(@ModelAttribute("author") Author author, Model model)
    {
        authorRepository.save(author);
        return "redirect:/authors";
    }



    // Get a Single Book
    @GetMapping("/authors/{id}")
    public String getAuthorById(@PathVariable(value = "id") Long authorId, Model model) throws AuthorNotFoundException {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        model.addAttribute("author", author);
        return "editAuthor";

    }

    // Update an Existing Book
    @PutMapping("/save_author")
    public String updateAuthor(@ModelAttribute("author") Author author, Model model)
            throws BookNotFoundException{
        authorRepository.save(author);
        return "redirect:/authors";
    }

    // Delete a Book
    @DeleteMapping("/delete_author/{id}")
    public String deleteAuthor(@PathVariable(value = "id") Long authorId, Model model) throws AuthorNotFoundException {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        authorRepository.delete(author);
        return "redirect:/authors";
    }





}