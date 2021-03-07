package woowang.board.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import woowang.board.domain.Post;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private EntityManager em;

    public void save(Post post) {
        em.persist(post);
    }

    public Post findOne(Long id) {
        return em.find(Post.class, id);
    }

    //TODO : 검색 했을떄 찾기

//    public List<Post> findAll() {
//        return em.createQuery("select p from Post p",Post.class).getResultList();
//    }
}
