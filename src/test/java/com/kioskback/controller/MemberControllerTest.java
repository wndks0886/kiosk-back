package com.kioskback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kioskback.domain.request.JoinRequest;
import com.kioskback.domain.request.LoginRequest;
import com.kioskback.domain.MemberEntity;
import com.kioskback.exception.KioskApplicationException;
import com.kioskback.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

    @Test
    public void 회원가입() throws Exception {
        String loginId = "loginId";
        String password = "123";

        when(memberService.join(loginId, password)).thenReturn(mock(MemberEntity.class));

        mockMvc.perform(post("/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new JoinRequest(loginId, password)))
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 회원가입시_이미_회원가입된_loginId로_회원가입_하는경우_에러반환() throws Exception{
        String loginId = "loginId";
        String password = "123";

        when(memberService.join(loginId, password)).thenThrow(new RuntimeException());

        mockMvc.perform(post("/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new JoinRequest(loginId, password)))
                ).andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void 로그인() throws Exception {
        String loginId = "loginId";
        String password = "123";

        when(memberService.login(loginId, password)).thenReturn("test_token");

        mockMvc.perform(post("/member/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new LoginRequest(loginId, password)))
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 로그인시_회원가입이_안된_loginId를_입력한경우_에러반환() throws Exception {
        String loginId = "loginId";
        String password = "123";

        when(memberService.login(loginId, password)).thenThrow(new KioskApplicationException());

        mockMvc.perform(post("/member/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new LoginRequest(loginId, password)))
                ).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void 로그인시_틀린_password를_입력한경우_에러반환() throws Exception {
        String loginId = "loginId";
        String password = "123";

        when(memberService.login(loginId, password)).thenThrow(new KioskApplicationException());

        mockMvc.perform(post("/member/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new LoginRequest(loginId, password)))
                ).andDo(print())
                .andExpect(status().isUnauthorized());
    }
}
