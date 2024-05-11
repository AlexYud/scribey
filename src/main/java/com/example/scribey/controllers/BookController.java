package com.example.scribey.controllers;

import com.example.scribey.domain.book.Book;
import com.example.scribey.domain.book.BookRepository;
import com.example.scribey.domain.book.RequestBookDTO;
import com.example.scribey.domain.user.User;
import com.example.scribey.infra.security.TokenService;
import com.example.scribey.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity getAllBooks() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity registerBook(@RequestBody @Valid RequestBookDTO data, HttpServletRequest request) {
        var token = tokenService.recoverToken(request);
        var email = tokenService.validateToken(token);
        UserDetails userDetails = userRepository.findByEmail(email);
        if (userDetails instanceof User) {
            User user = (User) userDetails;
            Book newBook = new Book(data, user.getId());
            repository.save(newBook);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateBook(@RequestBody @Valid RequestBookDTO data) {
        Optional<Book> optionalBook = repository.findById(data.id());
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
    public ResponseEntity deleteBook(@RequestBody @Valid RequestBookDTO data) {
        Optional<Book> optionalBook = repository.findById(data.id());
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            repository.delete(book);
            return ResponseEntity.noContent().build();
        } else {
            throw new EntityNotFoundException();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getBook(@PathVariable String id) {
        Optional<Book> optionalBook = repository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            return ResponseEntity.ok(book);
        } else {
            throw new EntityNotFoundException();
        }
    }
}