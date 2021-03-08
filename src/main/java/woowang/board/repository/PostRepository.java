package woowang.board.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import woowang.board.controller.PostSearch;
import woowang.board.domain.Post;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public void save(Post post) {
            em.persist(post);
    }

    public void edit(Long id, String title, String content) {
        Post findPost = em.find(Post.class, id);


    }

    public Post findOne(Long id) {
        return em.find(Post.class, id);
    }

    //TODO : 검색 했을떄 찾기

    public List<Post> findAll(PostSearch postSearch) {
        //language=JPAQL
        String jpql = "select p From Post p join p.member m";
        boolean isFirstCondition = true;
        //주문 상태 검색
        if (StringUtils.hasText(postSearch.getPostTitle())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " p.title like :title";
        }
        //회원 이름 검색
        if (StringUtils.hasText(postSearch.getMemberName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.name like :name";
        }
        TypedQuery<Post> query = em.createQuery(jpql, Post.class).setMaxResults(1000); //최대 1000건
        if (StringUtils.hasText(postSearch.getPostTitle())) {
            query = query.setParameter("title", postSearch.getPostTitle());
        }
        if (StringUtils.hasText(postSearch.getMemberName())) {
            query = query.setParameter("name", postSearch.getMemberName());
        }
        return query.getResultList();
    }
}
