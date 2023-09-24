package com.kioskback.repository;

import com.kioskback.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void saveTest(){
        Member member1 = new Member();
        member1.setMemberId("chk");
        member1.setPassword("1234");
        member1.setCreateDate(LocalDateTime.now());
        this.memberRepository.save(member1);

        System.out.println(member1);

        Member member2 = new Member();
        member2.setMemberId("chk");
        member2.setPassword("1234");
        member2.setCreateDate(LocalDateTime.now());
        this.memberRepository.save(member2);

        System.out.println(member2);
    }

}