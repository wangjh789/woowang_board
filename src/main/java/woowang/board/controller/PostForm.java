package woowang.board.controller;


import lombok.Getter;
import lombok.Setter;
import woowang.board.domain.Member;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
public class PostForm {

    @NotEmpty(message = "필수 항목입니다.")
    private String title;
    @NotEmpty(message = "필수 항목입니다.")
    private String content;
//    @NotEmpty(message = "필수 항목입니다.")
//    private String category;

}
