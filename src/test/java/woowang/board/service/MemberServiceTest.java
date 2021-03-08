package woowang.board.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import woowang.board.domain.Gender;
import woowang.board.domain.Member;
import woowang.board.repository.MemberRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = Member.createMember("가나다", "woowang@gmail.com", Gender.MAN);

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원가입() throws Exception{
        //given
        Member member1 = Member.createMember("가나다", "woowang@gmail.com", Gender.MAN);
        Member member2 = Member.createMember("가나다22", "woowang@gmail.com", Gender.MAN);

        memberService.join(member1);
        //when
        memberService.join(member2);

        //then
        fail("이메일이 이미 등록된 경우 예외가 발생해야 한다.");
    }

}