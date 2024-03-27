package com.ladharitech.ladhari.controller;

import com.ladharitech.ladhari.dto.RegisterRequest;
import com.ladharitech.ladhari.response.Response;
import com.ladharitech.ladhari.service.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/superadmin")
@SecurityRequirement(name="bearerAuth")
@Tag(name = "Super Admin")
@CrossOrigin("*")
@RequiredArgsConstructor
public class SuperAdminController {

    private final AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<String> hellouser(){
        return ResponseEntity.ok("Hello super Admin!");
    }

    @PostMapping("/addadmin")
    public ResponseEntity<Response>addAdmin(@RequestBody RegisterRequest request){return ResponseEntity.ok(authenticationService.registeradmin(request));}


}
