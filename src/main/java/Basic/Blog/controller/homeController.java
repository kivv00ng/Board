package Basic.Blog.controller;

import Basic.Blog.domain.Member;
import Basic.Blog.repository.MemberRepository;
import Basic.Blog.service.MemberService;
import Basic.Blog.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class homeController {

    private final MemberService memberService;

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model){

        HttpSession session = request.getSession(false);

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
