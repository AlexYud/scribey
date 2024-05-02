package com.example.scribey.controllers;

import com.example.scribey.domain.book.Book;
import com.example.scribey.domain.book.BookRepository;
import com.example.scribey.domain.book.RequestBookDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookRepository repository;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Object> registerBook(@RequestBody @Valid RequestBookDTO data) {
        System.out.println(data);
        return ResponseEntity.ok().build();
    }
}