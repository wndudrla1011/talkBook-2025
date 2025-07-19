package com.rootable.backend.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@NoArgsConstructor
@ToString(exclude = "postsList")
public class Book extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id; // 도서 ID

    @Column(name = "book_title", nullable = false, length = 30)
    private String title; // 도서 제목

    @Column(name = "book_writer", nullable = false, length = 30)
    private String writer; // 도서 작가

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre; // 장르

    @Enumerated(EnumType.STRING)
    @Column(name = "challenge_status")
    private ChallengeStatus status; // 챌린지 선정 상태

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Posts> postsList = new ArrayList<>();

    @Builder
    public Book(String title, String writer, Genre genre, ChallengeStatus status) {
        this.title = title;
        this.writer = writer;
        this.genre = genre;
        this.status = status;
    }

}
