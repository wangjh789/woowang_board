package woowang.board.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import woowang.board.domain.Gender;
import woowang.board.domain.Member;

import javax.validation.constraints.NotEmpty;

@Getter
public class MemberDto {

    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
    @NotEmpty
    private String gender;

    public Member toMember(){
        return Member.createMember(this.name, this.email, this.gender.equals("남자")?Gender.MAN:Gender.WOMAN);
    }

    public static MemberDto createMemberDto(String name, String email, String gender) {
        MemberDto memberDto = new MemberDto();
        memberDto.name = name;
        memberDto.email = email;
        memberDto.gender = gender;

        return memberDto;
    }

    public static MemberDto fromMember(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.id = member.getId();
        memberDto.name = member.getName();
        memberDto.email = member.getEmail();
        memberDto.gender = member.getGender() == Gender.MAN ? "남자" : "여자";

        return memberDto;
    }

}
