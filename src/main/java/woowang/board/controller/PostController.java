package woowang.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import woowang.board.domain.Comment;
import woowang.board.domain.Post;
import woowang.board.service.CategoryService;
import woowang.board.service.CommentService;
import woowang.board.service.MemberService;
import woowang.board.service.PostService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@Slf4j  //로그 하기 위해
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final MemberService memberService;
    private final CategoryService categoryService;
    private final CommentService commentService;

    @GetMapping("posts/create")
    public String createPost(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "posts/createPostForm";
    }

    @PostMapping("posts/create")
    public String create(@Valid PostForm postForm, BindingResult result) {
        if (result.hasErrors()) {
            return "posts/createPostForm";
        }
        Post post = Post.createPost(memberService.findMembers().get(0), categoryService.findAll().get(0), postForm.getTitle(), postForm.getContent());
        postService.savePost(post);
        return "redirect:/";
    }

    @GetMapping("posts")
    public String postList(@ModelAttribute("postSearch") PostSearch postSearch,Model model) {
        List<Post> posts = postService.findPosts(postSearch);
        model.addAttribute("posts", posts);
        return "posts/postList";
    }

    @GetMapping("posts/{postId}/show")
    public String showPost(@PathVariable("postId") Long postId,Model model){
        Post post = postService.findOne(postId);
        model.addAttribute("post", post);
        return "posts/showPost";
    }

    @PostMapping("posts/{postId}/show")
    public String addComment(@PathVariable("postId") Long postId,@ModelAttribute("comment") String comment){
        log.info("댓글 남기기");
        Post post = postService.findOne(postId);
        Comment newComment = Comment.createComment(memberService.findMembers().get(0), post, comment, LocalDateTime.now());
        commentService.saveComment(newComment);

        return "posts/showPost";
    }


}
