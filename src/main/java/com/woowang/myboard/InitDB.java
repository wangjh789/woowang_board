package com.woowang.myboard;

import com.woowang.myboard.model.ERole;
import com.woowang.myboard.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

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
        }

    }
}
