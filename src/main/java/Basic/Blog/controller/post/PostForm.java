package Basic.Blog.controller.post;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class PostForm {

    Long Id;
    @NotEmpty
    String title;
    @NotEmpty
    String content;



}
