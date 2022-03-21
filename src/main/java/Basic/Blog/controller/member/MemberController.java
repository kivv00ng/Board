package Basic.Blog.controller.member;

import Basic.Blog.domain.Member;
import Basic.Blog.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/add")
    public String addMemberForm(Model model){
        model.addAttribute("memberForm", new MemberForm());

        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String addMember(@ModelAttribute MemberForm memberForm, BindingResult bindingResult){
        //검증 로직

        //필드 오류
        if (!StringUtils.hasText(memberForm.getEmail())){
            bindingResult.addError(new FieldError("memberForm","Email", "Email은 필수항목 입니다."));
        }
        if (!StringUtils.hasText(memberForm.getName())){
            bindingResult.addError(new FieldError("memberForm","name", "이름은 필수 항목입니다."));
        }
        if (!StringUtils.hasText(memberForm.getPassword())){
            bindingResult.addError(new FieldError("memberForm","password", "비밀번호는 필수 항목입니다."));
        }
        //글로벌 오류
        if(!(memberService.findByEmail(memberForm.getEmail()).isEmpty())){
            bindingResult.addError(new ObjectError("memberForm",null,null,"이미 존재하는 회원입니다."));
        }

        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            return "members/addMemberForm";
        }

        //성공 로직
        Member member = Member.CreateMember(memberForm.getEmail(), memberForm.getName(), memberForm.getPassword());

        memberService.save(member);

        return "redirect:/";
    }


}
