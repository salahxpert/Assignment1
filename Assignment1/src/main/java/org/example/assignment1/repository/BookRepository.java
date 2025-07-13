package org.example.assignment1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.assignment1.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends     JpaRepository<Book, Long> { }
