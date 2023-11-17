package com.kioskback.controller;

import com.kioskback.domain.MemberEntity;
import com.kioskback.domain.request.JoinRequest;
import com.kioskback.domain.request.LoginRequest;
import com.kioskback.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm(Model model){
        model.addAttribute("joinRequest", new JoinRequest());

        return "join";
    }

    @PostMapping("/join")
    public String join(@Valid @ModelAttribute JoinRequest joinRequest, BindingResult bindingResult, Model model){
        // loginId 중복 확인 TODO: 컨트롤러? 서비스?
        if(memberService.checkLoginIdDuplicate(joinRequest.getLoginId())) {
            bindingResult.addError(new FieldError("joinRequest", "loginId", "로그인 아이디가 중복됩니다."));
        }
        
        // password와 passwordCheck 같은지 확인
        if(!joinRequest.getPassword().equals(joinRequest.getPasswordCheck())) {
            bindingResult.addError(new FieldError("joinRequest", "passwordCheck", "비밀번호가 일치하지 않습니다."));
        }

        if(bindingResult.hasErrors()) {
            return "join";
        }

        memberService.join(joinRequest);

        return "home";
    }

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("loginRequest", new LoginRequest());

        return "login";
    }

    @GetMapping("/info")
    public String infoForm(Model model, Authentication auth) {
        MemberEntity loginMember = memberService.getLoginMemberByLoginId(auth.getName());

        if(loginMember == null) {
            return "redirect:/member/login";
        }

        model.addAttribute("member", loginMember);

        return "info";
    }

}
