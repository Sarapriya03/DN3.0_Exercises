package com.example.bookstoreapi.service;

import com.example.bookstoreapi.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private List<Book> books = new ArrayList<>();

    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst();
    }

    @Override
    public Book createBook(Book book) {
        books.add(book);
        return book;
    }

    @Override
    public Optional<Book> updateBook(Long id, Book updatedBook) {
        if (bookRepository.existsById(id)) {
            updatedBook.setId(id); // Ensure the ID is set correctly
            Book savedBook = bookRepository.save(updatedBook);
            return Optional.of(savedBook);
        } else {
            return Optional.empty(); // Return an empty Optional if the book does not exist
        }
    }

    @Override
    public void deleteBook(Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }
}

