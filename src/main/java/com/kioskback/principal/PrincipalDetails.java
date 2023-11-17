package com.kioskback.principal;

import com.kioskback.domain.MemberEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private MemberEntity member;

    public PrincipalDetails(MemberEntity member) {
        this.member = member;
    }

    // 권한 관련 작업을 위한 role 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collections = new ArrayList<>();
        collections.add(() -> member.getRole().name());

        return collections;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getLoginId();
    }

    // 계정 만료 여부
    @Override
    public boolean isAccountNonExpired() {
        return true; // 만료 x
    }

    // 계정 잠금 여부
    @Override
    public boolean isAccountNonLocked() {
        return true; // 잠금 x
    }

    // 비밀번호 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 만료 x
    }

    // 계정 활성화 여부
    @Override
    public boolean isEnabled() {
        return true; // 사용 가능
    }
}
