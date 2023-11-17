package com.kioskback;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // spring의 환경설정 파일을 의미
@EnableWebSecurity // 모든 요청 URL이 spring security의 제어를 받도록 함
public class SecurityConfig {

    //spring security는 기본적으로 인증되지 않은 사용자는 서비스를 사용할 수 없게끔 되어 있음. -> 기본 설정 해제하는 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().disable() // 켜져있으면 h2-console에 접근이 안됨.
                .authorizeRequests() // 인증, 인가가 필요한 url 지정
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .usernameParameter("loginId")
                .passwordParameter("password")
                .loginPage("/member/login")
                .defaultSuccessUrl("/")
                .failureUrl("/member/login")
                .and()
                .logout()
                .logoutUrl("/member/logout")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID");

        return http.build();
    }

    // 비밀번호 암호화 => 암호화, 체크할 때 사용 (Service)
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
