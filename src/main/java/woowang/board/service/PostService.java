package woowang.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woowang.board.domain.Post;
import woowang.board.repository.PostRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    /**
     *  게시글 작성 , 수정 시
     */
    @Transactional
    public Long savePost(Post post){
        postRepository.save(post);
        return post.getId();
    }

    /**
     * 게시글 하나
     */
    public Post findOne(Long id) {
        return postRepository.findOne(id);
    }

}
