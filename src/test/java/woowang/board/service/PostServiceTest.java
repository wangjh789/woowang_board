package woowang.board.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import woowang.board.domain.Category;
import woowang.board.domain.Gender;
import woowang.board.domain.Member;
import woowang.board.domain.Post;
import woowang.board.repository.CategoryRepository;
import woowang.board.repository.MemberRepository;
import woowang.board.repository.PostRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private CategoryService categoryService;



    @Test
    public void 저장() throws Exception {
        //given
        Member member = Member.createMember("name", "email", Gender.MAN);
        memberService.join(member);
        Category category = Category.createCategory("temp_category");
        categoryService.save(category);

        //when
        Post post = Post.createPost(member, category, "temp_title", "temp_content");
        postService.savePost(post);

        //then
        assertEquals(post, postService.findOne(post.getId()));
    }

    @Test
    public void 수정() throws Exception {
        //given
        Member member = Member.createMember("name", "email", Gender.MAN);
        memberService.join(member);
        Category category = Category.createCategory("temp_category");
        categoryService.save(category);
        Post post = Post.createPost(member, category, "temp_title", "temp_content");
        postService.savePost(post);

        //when
        Category category2 = Category.createCategory("temp_category2");
        categoryService.save(category2);

        post.editPost(category2,"real_title","real_content");


        //then
        assertEquals(postRepository.findOne(post.getId()).getTitle(), "real_title");
    }

}