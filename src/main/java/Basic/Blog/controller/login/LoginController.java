package Basic.Blog.controller.login;

import Basic.Blog.domain.Member;
import Basic.Blog.service.LoginService;
import Basic.Blog.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginForm loginForm){
        return "login/loginForm";

    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm loginform, BindingResult bindingResult, @RequestParam(defaultValue = "/") String redirectURL, HttpServletRequest request){
        if (!StringUtils.hasText(loginform.getEmail())){
            bindingResult.addError(new FieldError("loginForm","Email", "Email을 입력하세요."));
        }
        if (!StringUtils.hasText(loginform.getPassword())){
            bindingResult.addError(new FieldError("loginForm","password", "비밀번호를 입력하세요."));
        }

        if(loginService.login(loginform.getEmail(), loginform.getPassword()) == null){
            bindingResult.addError(new ObjectError("loginForm",null,null,"로그인 정보가 일치하지 않습니다."));
        }

        if (bindingResult.hasErrors()){
            return "login/loginForm";
        }
        Member loginMember = loginService.login(loginform.getEmail(), loginform.getPassword());

        if(loginMember == null){
            bindingResult.addError(new ObjectError("loginForm",null,null,"로그인정보가 일치하지 않습니다."));
            return "login/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);


        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

}
