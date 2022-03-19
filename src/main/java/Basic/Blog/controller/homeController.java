package Basic.Blog.controller;

import Basic.Blog.domain.Member;
import Basic.Blog.repository.MemberRepository;
import Basic.Blog.service.MemberService;
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
    public String home(@CookieValue(name="memberId", required = false) Long memberId, Model model){

        if(memberId == null){
            return "home";
        }
        Optional<Member> loginMember = memberService.findById(memberId);

        if(loginMember.isEmpty()){
            return "home";
        }

        model.addAttribute("member", loginMember.get());
        return "loginHome";
    }
}
