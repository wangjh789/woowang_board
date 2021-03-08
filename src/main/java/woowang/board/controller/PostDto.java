package woowang.board.controller;


import lombok.Getter;
import woowang.board.domain.Member;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
public class PostDto {

    private Long id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    private Long categoryId;

    private Long memberId;
    private LocalDateTime dateTime;

//    public static PostDto createPostDto() {
//        PostDto postDto = new PostDto();
//        postDto
//    }
}
