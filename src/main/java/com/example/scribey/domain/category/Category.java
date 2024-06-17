package com.example.scribey.domain.category;

import com.example.scribey.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "category")
@Entity(name = "category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    private String created_at;
    private String updated_at;
    private String deleted_at;
}
