package com.example.scribey.domain.book;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

@Table(name = "books")
@Entity(name = "books")
@EqualsAndHashCode(of = "id")
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    private String likes;
}
