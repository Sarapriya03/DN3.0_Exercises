package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.mapper.BookMapper;
import com.example.bookstoreapi.model.Book;
import com.example.bookstoreapi.service.BookService;
import com.example.bookstoreapi.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookMapper bookMapper;

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        BookDTO bookDTO = bookMapper.toDto(book);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<BookDTO> bookDTOs = books.stream()
                                      .map(bookMapper::toDto)
                                      .collect(Collectors.toList());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "CustomHeaderValue");
        return new ResponseEntity<>(bookDTOs, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        Book createdBook = bookService.createBook(book);
        BookDTO createdBookDTO = bookMapper.toDto(createdBook);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/books/" + createdBookDTO.getId());
        return new ResponseEntity<>(createdBookDTO, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        book.setId(id);
        Book updatedBook = bookService.updateBook(id, book)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        BookDTO updatedBookDTO = bookMapper.toDto(updatedBook);
        return new ResponseEntity<>(updatedBookDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
