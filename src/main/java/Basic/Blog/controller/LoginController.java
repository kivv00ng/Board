package Basic.Blog.controller;

import Basic.Blog.domain.Member;
import Basic.Blog.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
    public String login(@Valid @ModelAttribute LoginForm loginform, BindingResult bindingResult, HttpServletResponse response){
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

        //쿠키 생성 로직
        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
        response.addCookie(idCookie);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response){
        Cookie cookie = new Cookie("memberId", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "redirect:/";
    }

}
