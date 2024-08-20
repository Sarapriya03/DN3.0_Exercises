package com.example.bookstoreapi.service;

import com.example.bookstoreapi.model.Book;
import com.example.bookstoreapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> updateBook(Long id, Book updatedBook) {
        if (bookRepository.existsById(id)) {
            updatedBook.setId(id); // Ensure the ID is set correctly
            return Optional.of(bookRepository.save(updatedBook));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> getBooksByCriteria(String title, String author) {
        if (title != null && author != null) {
            return bookRepository.findByTitleAndAuthor(title, author);
        } else if (title != null) {
            return bookRepository.findByTitle(title);
        } else if (author != null) {
            return bookRepository.findByAuthor(author);
        } else {
            return getAllBooks();
        }
    }
}
