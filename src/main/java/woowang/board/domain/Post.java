package woowang.board.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private String title;

    private String content;

    private LocalDateTime dateTime;


    //==연관관계 메소드==//
    private void setMember(Member member) {
        this.member = member;
        this.member.getPosts().add(this);
    }

    private void setCategory(Category category) {
        this.category = category;
        this.category.getPosts().add(this);
    }

    //==생성 메서드==//
    public static Post createPost(Member member, Category category, String title, String content) {
        Post post = new Post();
        post.setMember(member);
        post.setCategory(category);
        post.title = title;
        post.content = content;
        post.dateTime = LocalDateTime.now();
        return post;
    }

    //==변경 메소드==//
    public void editPost(Category category,String title,String content) {
        this.editCategory(category);
        this.title = title;
        this.content = content;
        this.dateTime = LocalDateTime.now();
    }
    private void editCategory(Category newCategory) {
        this.category.getPosts().remove(this);
        setCategory(newCategory);
    }

}
