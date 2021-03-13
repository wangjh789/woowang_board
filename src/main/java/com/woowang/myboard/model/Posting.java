package com.woowang.myboard.model;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Posting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posting_id")
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime dateTime;

    @OneToMany(mappedBy = "posting")
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    //==비즈니스 로직 ==//
    public void edit(String title,String content) {
        this.title = title;
        this.content = content;
    }

    //==생성 메소드==//
    public static Posting createPosting(String title,String content,User user,Category category){
        Posting posting = new Posting();
        posting.title = title;
        posting.content = content;
        posting.dateTime = LocalDateTime.now();
        posting.setUser(user);
        posting.setCategory(category);

        return posting;
    }

    //==연관관계 메소드==//
    public void setUser(User user) {
        this.user = user;
        user.getPostings().add(this);
    }

    public void setCategory(Category category){
        this.category = category;
        category.getPostings().add(this);
    }
}
