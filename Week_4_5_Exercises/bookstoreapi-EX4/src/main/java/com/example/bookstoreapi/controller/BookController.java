package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.model.Book;
import com.example.bookstoreapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Endpoint to fetch a book by its ID using a path variable
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint to filter books based on query parameters like title and author
    @GetMapping
    public List<Book> getBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {
        return bookService.getBooksByCriteria(title, author);
    }
}
