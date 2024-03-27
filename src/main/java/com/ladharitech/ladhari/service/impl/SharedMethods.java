package com.ladharitech.ladhari.service.impl;

import com.ladharitech.ladhari.dto.RegisterRequest;
import com.ladharitech.ladhari.entity.user.Role;
import com.ladharitech.ladhari.entity.user.User;
import com.ladharitech.ladhari.repository.UserRepository;
import com.ladharitech.ladhari.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class SharedMethods {
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    public User modifierProfile(RegisterRequest request){
        var user=authenticationService.userConnected().getUser();
        return User.builder()
                .firstname(StringUtils.hasText(request.getFirstname()) ? request.getFirstname() : user.getFirstname())
                .lastname(StringUtils.hasText(request.getLastname()) ? request.getLastname() : user.getLastname())
                .email(StringUtils.hasText(request.getEmail()) ? request.getEmail() : user.getEmail())
                .password(StringUtils.hasText(request.getPassword()) ? passwordEncoder.encode(request.getPassword()) : user.getPassword())
                .isEnabled(!StringUtils.hasText(request.getEmail()))
                .build();
    }
}
