package woowang.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import woowang.board.domain.Category;
import woowang.board.domain.Gender;
import woowang.board.domain.Member;
import woowang.board.domain.Post;
import woowang.board.service.CategoryService;
import woowang.board.service.MemberService;
import woowang.board.service.PostService;

@Controller
@Slf4j  //로그 하기 위해
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final CategoryService categoryService;
    private final PostService postService;

    @RequestMapping("/")
    public String home() {
        if(memberService.findMembers().size() == 0){
            Member member = Member.createMember("test1", "woowang@gmail.com", Gender.MAN);
            memberService.join(member);
            Category category = Category.createCategory("temp");
            categoryService.save(category);
            Post post = Post.createPost(member, category, "post1", "content1");
            postService.savePost(post);
        }
        return "home";
    }
}
