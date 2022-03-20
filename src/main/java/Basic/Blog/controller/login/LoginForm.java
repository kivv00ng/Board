package Basic.Blog.controller.login;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class LoginForm {

    @NotEmpty
    private String Email;
    @NotEmpty
    private String password;
}
