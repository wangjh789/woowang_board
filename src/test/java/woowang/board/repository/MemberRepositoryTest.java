package woowang.board.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import woowang.board.domain.Gender;
import woowang.board.domain.Member;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberRepositoryTest {

    @Autowired
    EntityManager em;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 디비_저장 ()throws  Exception {
        //given
        Member member1 = Member.createMember("woowang","woowang@gmail.com", Gender.MAN);
        memberRepository.save(member1);

        //when
        Member member2 = memberRepository.findByName("woowang").get(0);

        //then
        assertEquals(member2.getId(),member1.getId());
    }


}