package com.kioskback.repository;

import com.kioskback.domain.MemberEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void saveTest(){
        MemberEntity member1 = new MemberEntity();
        member1.setMemberId("chk");
        member1.setPassword("1234");
        member1.setCreateDate(LocalDateTime.now());
        this.memberRepository.save(member1);

        System.out.println(member1);

        MemberEntity member2 = new MemberEntity();
        member2.setMemberId("chk");
        member2.setPassword("1234");
        member2.setCreateDate(LocalDateTime.now());
        this.memberRepository.save(member2);

        System.out.println(member2);
    }

}