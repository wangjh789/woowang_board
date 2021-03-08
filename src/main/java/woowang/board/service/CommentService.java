package woowang.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woowang.board.domain.Comment;
import woowang.board.repository.CommentRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    /**
     * 댓글 작성 시
     */
    @Transactional
    public Long saveComment(Comment comment) {
        commentRepository.save(comment);
        return comment.getId();
    }

    public Comment findOne(Long id) {
        return commentRepository.findOne(id);
    }

}
