package com.kioskback.domain.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    private String loginId;
    private String password;

}
