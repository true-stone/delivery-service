package org.example.delivery.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Comment("회원 정보")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("회원 내부 식별자")
    private Long id;

    @Column(nullable = false, unique = true)
    @Comment("사용자 아이디")
    private String username;

    @Column(nullable = false)
    @Comment("비밀번호")
    private String password;

    @Column(nullable = false)
    @Comment("사용자 이름")
    private String name;

    @Builder
    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }
}
