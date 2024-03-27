package com.ladharitech.ladhari.controller;

import com.ladharitech.ladhari.dto.ChangePasswordRequest;
import com.ladharitech.ladhari.dto.RegisterRequest;
import com.ladharitech.ladhari.response.AuthenticationResponse;
import com.ladharitech.ladhari.service.AuthenticationService;
import com.ladharitech.ladhari.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user")
@SecurityRequirement(name="bearerAuth")
@Tag(name = "User")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<String> hellouser(){
        return ResponseEntity.ok("Hello user!");
    }


    @PatchMapping(value = "/changePassword")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ){
        userService.changePassword(request,connectedUser);
        return ResponseEntity.accepted().build();
    }

    @PutMapping(value = "/changerProfile")
    public ResponseEntity<?> changeProfile(
            @RequestBody RegisterRequest request
    )
    {
        userService.modifierProfileUser(request);
        return ResponseEntity.accepted().build();
    }

    @RequestMapping(value="/resend-confirm", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<AuthenticationResponse> reconfirmUserAccount()
    {
        return ResponseEntity.ok(authenticationService.reSend());

    }

}
