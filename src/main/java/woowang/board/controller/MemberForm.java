package woowang.board.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {

    @NotEmpty(message = "필수 항목입니다.")
    private String name;

    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @NotEmpty
    private String gender;
}
