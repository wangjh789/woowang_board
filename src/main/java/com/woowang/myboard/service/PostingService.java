package com.woowang.myboard.service;

import com.woowang.myboard.model.Category;
import com.woowang.myboard.model.Posting;
import com.woowang.myboard.model.User;
import com.woowang.myboard.repository.CategoryRepository;
import com.woowang.myboard.repository.PostingRepository;
import com.woowang.myboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostingService {

    private final PostingRepository postingRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long addPosting(Long userId, Long categoryId, String title, String content) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Error: User is not found."));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Error: Category is not found."));

        Posting posting = Posting.createPosting(title, content, user, category);

        postingRepository.save(posting);
        return posting.getId();
    }

    @Transactional
    public void editPosting(Posting posting) {
        postingRepository.save(posting);
    }


}
