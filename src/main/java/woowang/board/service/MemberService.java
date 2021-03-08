package woowang.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woowang.board.domain.Member;
import woowang.board.repository.MemberRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateEmail(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateEmail(Member member) {
        List<Member> result = memberRepository.findByEmail(member.getEmail());
        if (!result.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 이메일 입니다.");
        }
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 단일 회원 조회
     */
    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }

}
