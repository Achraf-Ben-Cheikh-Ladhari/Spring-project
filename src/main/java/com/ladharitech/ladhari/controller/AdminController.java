package com.ladharitech.ladhari.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@SecurityRequirement(name="bearerAuth")
@Tag(name = "Admin")
@CrossOrigin("*")
public class AdminController {

    @GetMapping
    public ResponseEntity<String> helloadmin(){
        return ResponseEntity.ok("Hello admin!");
    }
}
