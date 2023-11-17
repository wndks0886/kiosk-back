package com.kioskback.controller;

import com.kioskback.domain.MemberEntity;
import com.kioskback.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/")
public class HomeController {

    private final MemberService memberService;

    @GetMapping(value = {"", "/"})
    public String home(Model model, Authentication auth){
        if(auth != null){
            MemberEntity loginMember = memberService.getLoginMemberByLoginId(auth.getName());
            if(loginMember != null){
                model.addAttribute("loginId", loginMember.getLoginId());
            }
        }

        return "home";
    }
}
