package woowang.board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy ="category")
    private List<Post> posts = new ArrayList<>();

    //==생성 메서드==//
    public static Category createCategory(String name) {
        Category category = new Category();
        category.name = name;
        return category;
    }

}
