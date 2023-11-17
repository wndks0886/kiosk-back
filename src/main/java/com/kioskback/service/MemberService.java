package com.kioskback.service;

import com.kioskback.domain.MemberEntity;
import com.kioskback.domain.request.JoinRequest;
import com.kioskback.domain.request.LoginRequest;
import com.kioskback.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

//    private final BCryptPasswordEncoder encoder;

    // 회원가입
    public MemberEntity join(JoinRequest joinRequest) {
        // loginId 중복 여부


        // 회원가입 진행
        memberRepository.save(joinRequest.toEntity(passwordEncoder().encode(joinRequest.getPassword())));

        return new MemberEntity();
    }

    // 로그인
    public MemberEntity login(LoginRequest loginRequest) {
        // 회원가입 여부
        Optional<MemberEntity> optionalMember = memberRepository.findByLoginId(loginRequest.getLoginId());

        if(optionalMember.isEmpty()) {
            return null;
        }

        MemberEntity member = optionalMember.get();

        // 비밀번호 체크
        if(!member.getPassword().equals(loginRequest.getPassword())) {
            return null;
        }

        return member;
    }
    
    // id(Long)를 입력받아 해당하는 Member 리턴
    public MemberEntity getLoginMemberById(Long id) {
        if(id == null) {
            return null;
        }

        Optional<MemberEntity> optionalMember = memberRepository.findById(id);

        if(optionalMember.isEmpty()) {
            return null;
        }

        return optionalMember.get();
    }

    // loginId(String)를 입력받아 해당하는 Member 리턴
    public MemberEntity getLoginMemberByLoginId(String loginId) {
        // 입력받은 값이 없으면 null 리턴
        if(loginId == null) {
            return null;
        }

        Optional<MemberEntity> optionalMember = memberRepository.findByLoginId(loginId);

        // 해당하는 Member가 없으면 null 리턴
        if(optionalMember.isEmpty()) {
            return null;
        }

        return optionalMember.get();
    }

    // loginId 중복 체크
    public boolean checkLoginIdDuplicate(String loginId) {
        return memberRepository.existsByLoginId(loginId);
    }
    
    // 비밀번호 암호화
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
