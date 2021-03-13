package com.woowang.myboard.service;

import com.woowang.myboard.model.Category;
import com.woowang.myboard.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long addCategory(String name) {
        Category category = Category.createCategory(name);
        categoryRepository.save(category);

        return category.getId();
    }
}
