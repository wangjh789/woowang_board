package woowang.board.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import woowang.board.domain.Category;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final EntityManager em;

    public void save(Category category) {
        em.persist(category);
    }

    public Category findOne(Long id) {
        return em.find(Category.class, id);
    }

    public List<Category> findByName(String name) {
        return em.createQuery("select c from Category c where c.name =:name", Category.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Category> findAll() {
        return em.createQuery("select c from Category c",Category.class).getResultList();
    }
}
