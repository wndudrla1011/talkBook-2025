package com.rootable.backend.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id; // 댓글 아이디

    @Column(name = "contents", nullable = false, length = 1000)
    private String contents; // 댓글 내용

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Posts posts; // 댓글이 작성된 글

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member; // 댓글 작성자

    @Builder
    public Comment(String contents, Posts posts, Member member) {
        this.contents = contents;
        this.posts = posts;
        this.member = member;
    }

    /**
     * 연관 관계 메서드
     */
    public void bindPosts(Posts posts) {
        this.posts = posts;
        posts.getCommentList().add(this);
    }

    public void bindMember(Member member) {
        this.member = member;
        member.getCommentList().add(this);
    }

    public void update(String contents) {
        this.contents = contents;
    }

}
