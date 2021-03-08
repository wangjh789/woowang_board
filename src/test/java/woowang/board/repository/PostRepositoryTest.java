package woowang.board.repository;

import org.junit.Assert;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 저장() throws Exception {
        //given
        Category category = Category.createCategory("tempCategory");
        categoryRepository.save(category);

        Member member = Member.createMember("esttest", "woowang@gmail.com", Gender.MAN);
        memberRepository.save(member);

        //when
        Post post = Post.createPost(member, category, "temp_title", "temp_content");
        postRepository.save(post);

        //then
//        assertEquals(post.getTitle(),postRepository.findOne(post.getId()).get);
    }

}