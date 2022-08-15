package Basic.Board.controller.post;

import Basic.Board.domain.Comment;
import Basic.Board.domain.Member;
import Basic.Board.domain.Post;
import Basic.Board.service.CommentService;
import Basic.Board.service.MemberService;
import Basic.Board.service.PostService;
import Basic.Board.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final MemberService memberService;
    private final PostService postService;
    private final CommentService commentService;


    //comment post
    @PostMapping("/posts/detail/{postId}")
    public String addComment(@PathVariable Long postId, @ModelAttribute("commentForm") CommentForm commentForm, BindingResult bindingResult, HttpServletRequest request){

        if (!StringUtils.hasText(commentForm.getContent())){
            bindingResult.addError(new FieldError("commentForm","content", commentForm.getContent(), false, null, null,"내용을 입력하세요."));
        }

        if(bindingResult.hasErrors()){
            return "redirect:/posts/detail/{postId}";
        }

        //성공로직

        Comment comment = Comment.CreateComment(commentForm.getContent());
        Post post = postService.findOne(postId);

        //해당 멤버에 추가
        HttpSession session = request.getSession(false);
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Member findMember = memberService.findOne(loginMember.getId());

        findMember.addComment(post,comment);
        commentService.save(comment);

        return "redirect:/posts/detail/{postId}";
    }

    //댓글 삭제
    @PostMapping("/posts/detail/{postId}/comment/{commentId}/delete")
    public String deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return "redirect:/posts/detail/{postId}";
    }
}
