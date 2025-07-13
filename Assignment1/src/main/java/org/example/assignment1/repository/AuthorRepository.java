package org.example.assignment1.repository;

import org.example.assignment1.model.Author;
import org.example.assignment1.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> { }