package com.kioskback.service;

import com.kioskback.domain.Member;
import com.kioskback.domain.MemberForm;
import com.kioskback.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원가입
    public Member join(String memberId, String password){
        Member member = new Member();
        member.setMemberId(memberId);
        member.setPassword(password);
//        member.setCreateDate(LocalDateTime.now());

        this.memberRepository.save(member);

        return member;
    }

    // 회원 목록
    public List<Member> memberList(){
        List<Member> members = this.memberRepository.findAll();

        return members;
    }
}
