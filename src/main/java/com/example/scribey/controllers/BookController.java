package com.example.scribey.controllers;

import com.example.scribey.domain.book.Book;
import com.example.scribey.domain.book.BookRepository;
import com.example.scribey.domain.book.RequestBookDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookRepository repository;

    @GetMapping
    public ResponseEntity getAllBooks() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity registerBook(@RequestBody @Valid RequestBookDTO data) {
        Book newBook = new Book(data);
        repository.save(newBook);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity updateBook(@RequestBody @Valid RequestBookDTO data) {
        Optional<Book> optionalBook = repository.findById(data.id());
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(data.title());
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}