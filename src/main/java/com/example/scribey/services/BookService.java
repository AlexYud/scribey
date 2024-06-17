package com.example.scribey.services;

import com.example.scribey.domain.book.Book;
import com.example.scribey.repositories.BookRepository;
import com.example.scribey.domain.book.RequestBookDTO;
import com.example.scribey.domain.category.Category;
import com.example.scribey.repositories.CategoryRepository;
import com.example.scribey.domain.user.User;
import com.example.scribey.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }

    @Transactional
    public void save(RequestBookDTO requestBookDTO, String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Category> optionalCategory = categoryRepository.findById(requestBookDTO.categoryId());
        if (optionalUser.isPresent() && optionalCategory.isPresent()) {
            User user = optionalUser.get();
            Category category = optionalCategory.get();
            Book newBook = new Book(
                requestBookDTO.title(),
                requestBookDTO.description(),
                new Date().toString(),
                new Date().toString(),
                user,
                category
            );
            bookRepository.save(newBook);
        }
    }
}
