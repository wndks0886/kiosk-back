package com.kioskback.service;

import com.kioskback.domain.MemberEntity;
import com.kioskback.exception.KioskApplicationException;
import com.kioskback.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @MockBean
    private MemberRepository memberRepository;

    @Test
    public void 회원가입이_정상적으로_동작하는경우(){
        //given
        String loginId = "loginId";
        String password = "123";

        //when
        when(memberRepository.findByLoginId(loginId)).thenReturn(Optional.empty());
        when(memberRepository.save(any())).thenReturn(Optional.of(mock(MemberEntity.class)));

        //then
        Assertions.assertDoesNotThrow(() -> memberService.join(loginId, password));
    }

    @Test
    public void 회원가입시_loginId로_회원가입한_유저가_이미_있는경우(){
        //given
        String loginId = "loginId";
        String password = "123";

        //when
        when(memberRepository.findByLoginId(loginId)).thenReturn(Optional.of(mock(MemberEntity.class)));
        when(memberRepository.save(any())).thenReturn(Optional.of(mock(MemberEntity.class)));

        //then
        Assertions.assertThrows(KioskApplicationException.class, () -> memberService.join(loginId, password));
    }

    @Test
    public void 로그인이_정상적으로_동작하는경우(){
        //given
        String loginId = "loginId";
        String password = "123";

        //when
        when(memberRepository.findByLoginId(loginId)).thenReturn(Optional.empty());

        //then
        Assertions.assertDoesNotThrow(() -> memberService.login(loginId, password));
    }

//    @Test
//    public void 회원가입시_loginId로_회원가입한_유저가_이미_있는경우(){
//        //given
//        String loginId = "loginId";
//        String password = "123";
//
//        //when
//        when(memberRepository.findByLoginId(loginId)).thenReturn(Optional.of(mock(MemberEntity.class)));
//        when(memberRepository.save(any())).thenReturn(Optional.of(mock(MemberEntity.class)));
//
//        //then
//        Assertions.assertThrows(KioskApplicationException.class, () -> memberService.join(loginId, password));
//    }
}
