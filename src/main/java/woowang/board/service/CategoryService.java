package woowang.board.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woowang.board.domain.Category;
import woowang.board.repository.CategoryRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long save(Category category) {
        validateDuplicateCategory(category);
        categoryRepository.save(category);
        return category.getId();
    }

    private void validateDuplicateCategory(Category category) {
        List<Category> findCategories = categoryRepository.findByName(category.getName());
        if(!findCategories.isEmpty()){
            throw new IllegalStateException("이미 존재하는 카테고리입니다.");
        }
    }

    public Category findOne(Long id) {
        return categoryRepository.findOne(id);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

}
