package com.woowang.myboard.model;

import lombok.Data;

@Data
public class CommentForm {

    private String content;
    private User user;
    private Posting posting;
}
