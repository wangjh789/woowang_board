package woowang.board.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import woowang.board.domain.Comment;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private EntityManager em;

    public void save(Comment comment) {
        em.persist(comment);
    }

    public Comment findOne(Long id) {
        return em.find(Comment.class, id);
    }


    public List<Comment> findAll() {
        return em.createQuery("select c from Comment c",Comment.class).getResultList();
    }
}
