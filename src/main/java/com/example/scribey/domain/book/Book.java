package com.example.scribey.domain.book;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "book")
@Entity(name = "book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String title;
}
