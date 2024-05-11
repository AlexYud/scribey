package com.example.scribey.domain.book;

import com.example.scribey.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.context.SecurityContextHolder;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Book(RequestBookDTO requestBookDTO) {
        this.title = requestBookDTO.title();
//        (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
