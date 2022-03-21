package Basic.Blog.controller.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

    @NotEmpty()
    private String Email;

    private String name;
    private String password;
}
