package com.ladharitech.ladhari.controller;


import com.ladharitech.ladhari.dto.ChangePasswordRequest;
import com.ladharitech.ladhari.dto.LoginRequest;
import com.ladharitech.ladhari.dto.RegisterRequest;
import com.ladharitech.ladhari.entity.user.User;
import com.ladharitech.ladhari.response.AuthenticationResponse;
import com.ladharitech.ladhari.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentification")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity <AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<AuthenticationResponse> confirmUserAccount(@RequestParam("token")String confirmationToken)
    {
        return ResponseEntity.ok(authenticationService.confirm(confirmationToken));

    }

    @PostMapping("/login")
    public ResponseEntity <AuthenticationResponse> register(
            @RequestBody LoginRequest request
    ){
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentUser() {
        return ResponseEntity.ok(authenticationService.userConnected().isConnected());
    }

}
