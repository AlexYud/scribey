package com.example.scribey.book;

import jakarta.persistence.*;

@Table(name = "books")
@Entity(name = "books")
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    private String likes;
}
