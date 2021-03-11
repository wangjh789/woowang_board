package com.woowang.myboard.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Posting> postings = new ArrayList<>();

    //==생성 메소드==//
    public static Category createCategory(String name) {
        Category category = new Category();
        category.name = name;
        return category;
    }
}
