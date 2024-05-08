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
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;

    public Book(RequestBookDTO requestBookDTO) {
        this.title = requestBookDTO.title();
    }
}
