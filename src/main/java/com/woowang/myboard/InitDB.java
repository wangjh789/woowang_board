package com.woowang.myboard;

import com.woowang.myboard.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService; //loading 시점에서

    @PostConstruct //spring pool 라이프 때문에 Transactional 잘 안 먹혀서 분리
    public void init() {
        initService.dbInit1();

    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Role role1 = new Role(ERole.ROLE_ADMIN);
            em.persist(role1);

            Role role2 = new Role(ERole.ROLE_USER);
            em.persist(role2);

            Set<Role> roles = new HashSet<>();
            roles.add(role1);
            User user = new User("woowang", "wangjh789@gmail.com", "123123");
            user.setRoles(roles);
            em.persist(user);

            Category category = Category.createCategory("temp");
            em.persist(category);

            Posting posting1 = Posting.createPosting("임시 게시물1", "임시 내용1", user, category);
            em.persist(posting1);

            Posting posting2 = Posting.createPosting("임시 게시물1", "임시 내용1", user, category);
            em.persist(posting2);

            Comment comment1 = Comment.createComment("임시 댓글1", user, posting1);
            em.persist(comment1);

            Comment comment2 = Comment.createComment("임시 댓글2", user, posting1);
            em.persist(comment2);

        }

    }
}
