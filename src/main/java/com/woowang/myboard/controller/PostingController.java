package com.woowang.myboard.controller;

import com.woowang.myboard.model.Comment;
import com.woowang.myboard.model.CommentForm;
import com.woowang.myboard.model.Posting;
import com.woowang.myboard.model.User;
import com.woowang.myboard.repository.PostingRepository;
import com.woowang.myboard.repository.UserRepository;
import com.woowang.myboard.service.CommentService;
import com.woowang.myboard.service.PostingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostingController {

    private final PostingRepository postingRepository;
    private final PostingService postingService;
    private final CommentService commentService;
    private final UserRepository userRepository;

    @GetMapping("/postings")
    public String loadPostings(Model model) {
        List<Posting> postings = postingRepository.findAll();
        model.addAttribute("postings", postings);

        return "posting/postingList";
    }

    @GetMapping("postings/{postingId}")
    public String viewPosting(@PathVariable String postingId,Model model){
        Posting posting = postingRepository.findById(Long.parseLong(postingId)).get();
        model.addAttribute("posting", posting);
        model.addAttribute("commentForm", new CommentForm());
        return "posting/showPosting";
    }

    @PostMapping("postings/{postingId}/addComment")
    public String addComment(@PathVariable String postingId,@ModelAttribute("commentForm") CommentForm commentForm) {
        Posting findPosting = postingRepository.findById(Long.parseLong(postingId)).get();
        User findUser = userRepository.findAll().get(0);
//        Comment comment = Comment.createComment(commentForm.getContent(), findUser, findPosting);
        commentService.addComment(findUser.getId(), findPosting.getId(), commentForm.getContent());
        return "redirect:/postings/{postingId}";
    }

    @GetMapping("postings/{postingId}/edit")
    public String editPosting(@PathVariable String postingId,Model model){
        Posting posting = postingRepository.findById(Long.parseLong(postingId)).get();
        model.addAttribute("posting", posting);
        return "posting/editPosting";
    }

    @PostMapping("postings/{postingId}/edit")
    public String edit(@PathVariable String postingId,@ModelAttribute("posting") Posting posting){
        log.info(posting.getContent());
        log.info(posting.getTitle());
        Posting findPosting = postingRepository.findById(Long.parseLong(postingId)).get();
        findPosting.edit(posting.getTitle(),posting.getContent());
        postingService.editPosting(posting);

        return "redirect:/postings/{postingId}";
    }




}
