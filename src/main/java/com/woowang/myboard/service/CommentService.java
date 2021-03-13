package com.woowang.myboard.service;

import com.woowang.myboard.model.Comment;
import com.woowang.myboard.model.Posting;
import com.woowang.myboard.model.User;
import com.woowang.myboard.repository.CommentRepository;
import com.woowang.myboard.repository.PostingRepository;
import com.woowang.myboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostingRepository postingRepository;

    @Transactional
    public Long addComment(Long userId, Long postingId, String content) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Error: User is not found."));
        Posting posting = postingRepository.findById(postingId)
                .orElseThrow(() -> new RuntimeException("Error: Posting is not found."));

        Comment comment = Comment.createComment(content, user, posting);
        commentRepository.save(comment);

        return comment.getId();
    }
}
