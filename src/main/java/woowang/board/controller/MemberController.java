package woowang.board.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import woowang.board.domain.Gender;
import woowang.board.domain.Member;
import woowang.board.service.MemberService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j  //로그 하기 위해
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/create")
    public String createMember(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/create")
    public String create(@Valid MemberForm memberForm, BindingResult result) {
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }
        MemberDto memberDto = MemberDto.createMemberDto(memberForm.getName(), memberForm.getEmail(), memberForm.getGender());
        memberService.join(memberDto.toMember());
        return "redirect:/";
    }

    @GetMapping("/members")
    public String memberList(Model model) {
        List<MemberDto> members = new ArrayList<>();
        List<Member> memberList = memberService.findMembers();
        for(Member member:memberList){
            members.add(MemberDto.fromMember(member));
        }
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
