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
@ToString(exclude = {"postsList", "commentList"})
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id; // 회원 일련번호

    @Column(name = "member_name", nullable = false, length = 30)
    private String name; // 회원 이름

    @Column(unique = true, name = "member_login_id", nullable = false, length = 30)
    private String loginId; // 회원 로그인 아이디

    @Column(name = "member_password", nullable = false, length = 50)
    private String password; // 회원 비밀번호

    @Column(name = "member_email", nullable = false, length = 50)
    private String email; // 이메일

    @Column(name = "member_phone", nullable = false, length = 30)
    private String phone; // 휴대폰 번호

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // 권한

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Posts> postsList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @OrderBy("id desc")
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Member(String name, String loginId, String password, String email, String phone, Role role) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    // 계정 관련
    public void update(String loginId, String password, Role role) {
        this.loginId = loginId;
        this.password = password;
        this.role = role;
    }

}
