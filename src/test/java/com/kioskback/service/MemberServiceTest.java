package com.kioskback.service;

import com.kioskback.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    public void save(){
        //given
        Member member = new Member();
        member.setId(1L);
        member.setMemberId("chk");
        member.setPassword("1234");
        member.setCreateDate(LocalDateTime.now());

        //when
        memberService.join(member);

        //then
        System.out.println(member);
    }
}
