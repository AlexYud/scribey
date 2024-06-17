package com.example.scribey.domain.book;

import com.example.scribey.domain.category.Category;
import com.example.scribey.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

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
    private String description;
    private String created_at;
    private String updated_at;
    private String deleted_at;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    public Book(String title, String description, String created_at, String updated_at, User user, Category category) {
        this.title = title;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.user = user;
        this.category = category;
    }
}
