package com.rootable.backend.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@ToString(exclude = "bookList")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long id; // 장르 아이디

    @Column(name = "genre_name", nullable = false, length = 30, unique = true)
    private String name; // 장르명

    @OneToMany(mappedBy = "genre")
    private List<Book> bookList = new ArrayList<>();

    @Builder
    public Genre(String name) {
        this.name = name;
    }

}
