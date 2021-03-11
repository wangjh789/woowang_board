package com.woowang.myboard.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @NotBlank
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posting_id")
    private Posting posting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime dateTime;

    //==생성메소드==//
    public static Comment createComment(String content, User user, Posting posting) {
        Comment comment = new Comment();
        comment.content = content;
        comment.dateTime = LocalDateTime.now();
        comment.setUser(user);
        comment.setPosting(posting);
        return comment;
    }

    //==연관관계 메소드==//
    public void setUser(User user) {
        this.user = user;
        user.getComments().add(this);
    }

    public void setPosting(Posting posting) {
        this.posting = posting;
        posting.getComments().add(this);
    }
}
