package com.ladharitech.ladhari.dto;

import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String password;

}
