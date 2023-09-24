package com.kioskback.controller;

import com.kioskback.domain.Member;
import com.kioskback.domain.MemberForm;
import com.kioskback.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signup(MemberForm memberForm){
        return "signUpForm";
    }

    @PostMapping("/signup") // BindingResult -> Validator 변경하면 간결한 코드로 작성 가능하다고 한다.
    public String signup(@Valid MemberForm memberForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println("hasErrors");
            return "signUpForm";
        }

        if(!memberForm.getPassword1().equals(memberForm.getPassword2())){
            bindingResult.rejectValue("password2", "passwordIncorrect", "2개의 비밀번호가 일치하지 않습니다.");
            System.out.println("password1 and password2");
            return "signUpForm";
        }

        // 아이디 중복 처리 validation 필요

        memberService.join(memberForm.getMemberId(), memberForm.getPassword1());

        return "redirect:/";
    }

    @GetMapping("/memberlist")
    public String memberList(Model model){
        List<Member> members = memberService.memberList();
        model.addAttribute("members", members);

        return "memberList";
    }
}
