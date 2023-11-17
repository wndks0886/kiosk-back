package com.kioskback.domain.request;

import com.kioskback.domain.MemberEntity;
import com.kioskback.domain.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JoinRequest {

    @Size(min = 3, max = 15)
    @NotBlank(message = "ID를 입력해주세요.")
    private String loginId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "비밀번호 확인은 필수항목입니다.")
    private String passwordCheck;

    public MemberEntity toEntity(String encodedPassword){
        return MemberEntity.builder()
                .loginId(this.loginId)
                .password(encodedPassword)
                .role(MemberRole.USER)
                .build();
    }

}
