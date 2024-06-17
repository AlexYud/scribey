package com.example.scribey.controllers;

import com.example.scribey.domain.book.Book;
import com.example.scribey.domain.book.RequestBookDTO;
import com.example.scribey.domain.user.User;
import com.example.scribey.services.BookService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> registerBook(@RequestBody @Valid RequestBookDTO data, HttpServletRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        bookService.save(data, user.getId());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> updateBook(@RequestBody @Valid RequestBookDTO data) {
        Optional<Book> optionalBook = bookService.findById(data.id());
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(data.title());
            return ResponseEntity.ok(book);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> deleteBook(@RequestBody @Valid RequestBookDTO data) {
        Optional<Book> optionalBook = bookService.findById(data.id());
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            bookService.delete(book);
            return ResponseEntity.noContent().build();
        } else {
            throw new EntityNotFoundException();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBook(@PathVariable String id) {
        Optional<Book> optionalBook = bookService.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            return ResponseEntity.ok(book);
        } else {
            throw new EntityNotFoundException();
        }
    }
}