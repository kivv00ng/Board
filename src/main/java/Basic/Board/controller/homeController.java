package Basic.Board.controller;

import Basic.Board.domain.Member;
import Basic.Board.domain.Post;
import Basic.Board.service.MemberService;
import Basic.Board.service.PostService;
import Basic.Board.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class homeController {

    private final MemberService memberService;
    private final PostService postService;

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        List<Post> posts = postService.findAll();
        model.addAttribute("posts",posts);

        if(session == null){
            return "home";
        }
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        if(loginMember == null ){
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}
