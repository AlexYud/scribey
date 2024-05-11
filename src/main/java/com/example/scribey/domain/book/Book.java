package com.example.scribey.domain.book;

import com.example.scribey.domain.user.User;
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
    private String user_id;

    public Book(RequestBookDTO requestBookDTO, String user_id) {
        this.title = requestBookDTO.title();
        this.user_id = user_id;
    }
}
