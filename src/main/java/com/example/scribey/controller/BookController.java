package com.example.scribey.controller;

import com.example.scribey.book.Book;
import com.example.scribey.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookRepository repository;

    @GetMapping
    public List<Book> getAll() {
        return repository.findAll();
    }
}