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
@ToString(exclude = "commentList")
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id; // 게시글 번호

    @Column(name = "title", length = 50, nullable = false)
    private String title; // 제목

    @Column(name = "contents", columnDefinition = "TEXT")
    private String contents; // 내용

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member; //게시물 작성자

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "book_id")
    private Book book; // 챌린지 도서

    @Column(name = "file_id")
    private Long fileId; // 파일 ID

    @Enumerated(EnumType.STRING)
    private Status status; // 챌린지 수용 상태

    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
    @OrderBy("id asc") //댓글 정렬
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Posts(String title, String contents, Member member, Book book, Long fileId, Status status) {
        this.title = title;
        this.contents = contents;
        this.member = member;
        this.book = book;
        this.fileId = fileId;
        this.status = status;
    }

    /**
     * 연관 관계 메서드
     */
    public void bindMember(Member member) {
        this.member = member;
        member.getPostsList().add(this);
    }

    public void bindBook(Book book) {
        this.book = book;
        book.getPostsList().add(this);
    }

    public void update(String title, String contents, Book book, Status status) {
        this.title = title;
        this.contents = contents;
        this.book = book;
        this.status = status;
    }

    public void changeFile(Long fileId) {
        this.fileId = fileId;
    }

}
