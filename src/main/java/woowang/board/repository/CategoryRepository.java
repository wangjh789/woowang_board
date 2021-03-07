package woowang.board.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import woowang.board.domain.Category;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private EntityManager em;

    public void save(Category category) {
        em.persist(category);
    }

    public Category findOne(Long id) {
        return em.find(Category.class, id);
    }


    public List<Category> findAll() {
        return em.createQuery("select p from Post p",Category.class).getResultList();
    }
}
