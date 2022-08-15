package Basic.Board.controller.post;


import Basic.Board.domain.Comment;
import Basic.Board.domain.Member;
import Basic.Board.domain.Post;
import Basic.Board.service.CommentService;
import Basic.Board.service.MemberService;
import Basic.Board.service.PostService;
import Basic.Board.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final MemberService memberService;
    private final CommentService commentService;

    @GetMapping("/posts/add")
    public String addPostForm(Model model){
        model.addAttribute("postForm", new PostForm());
        return "posts/addPostForm";
    }

    @PostMapping("/posts/add")
    public String addPost(@ModelAttribute PostForm postForm, BindingResult bindingResult, HttpServletRequest request){
        //필드 오류
        if (!StringUtils.hasText(postForm.getTitle())){
            bindingResult.addError(new FieldError("postForm","title", postForm.getTitle(), false, null,null, "제목을 입력하세요."));
        }
        if (!StringUtils.hasText(postForm.getContent())){
            bindingResult.addError(new FieldError("postForm","content", postForm.getContent(), false, null, null,"내용을 입력하세요."));
        }

        if(bindingResult.hasErrors()){
            return "posts/addPostForm";
        }

        //성공 로직

            ////post생성
        Post post = Post.CreatePost(postForm.getTitle(), postForm.getContent());

            //해당 멤버에 추가
        HttpSession session = request.getSession(false);
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        log.info("###post_loginMember={}",loginMember);

        Member findMember = memberService.findOne(loginMember.getId());
        findMember.addPost(post);

        postService.save(post);

        return "redirect:/";
    }

    @GetMapping("/posts/detail/{postId}")
    public String post(@PathVariable Long postId, Model model, HttpServletRequest request){
        Post post = postService.findOne(postId);
        List<Comment> comments = post.getComments();

        Boolean loginCheck = false;

        //수정 버튼 생성 여부를 결정하기 위한 로그인 정보 확인
        HttpSession session = request.getSession(false);
        if(session !=null) {
            Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
            if (loginMember != null && loginMember.getEmail() == post.getMember().getEmail()) {
                loginCheck = true;
            }
            //댓글 작성자 확인
            for (Comment comment : comments) {
                if (loginMember != null && loginMember.getEmail() == comment.getMember().getEmail()) {
                    loginCheck = true;
                }

            }

        }

        model.addAttribute("post",post);
        model.addAttribute("loginCheck",loginCheck);

        model.addAttribute("comments", comments);
        model.addAttribute("commentForm", new CommentForm());

        return "posts/post";
    }

    @GetMapping("/posts/{postId}/edit")
    public String postUpdateForm(@PathVariable Long postId, Model model){
        Post post = postService.findOne(postId);

        PostForm postForm = new PostForm();

        postForm.setId(post.getId());
        postForm.setTitle(post.getTitle());
        postForm.setContent(post.getContent());

        model.addAttribute("postForm", postForm);

        return "posts/postEditForm";
    }

    @PostMapping("/posts/{postId}/edit")
    public String postUpdate(@PathVariable Long postId, @ModelAttribute("postForm") PostForm postForm, HttpServletRequest request){

        postService.updatePost(postId, postForm.getTitle(), postForm.getContent() );

        return "redirect:/posts/detail/{postId}";
    }

}
