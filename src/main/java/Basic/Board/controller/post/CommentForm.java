package Basic.Board.controller.post;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter @Setter
public class CommentForm {

    @NotEmpty
    private String content;

    List<Boolean> juinChecks;
}
