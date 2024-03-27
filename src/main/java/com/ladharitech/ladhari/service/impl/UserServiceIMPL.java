package com.ladharitech.ladhari.service.impl;

import com.ladharitech.ladhari.dto.ChangePasswordRequest;
import com.ladharitech.ladhari.dto.RegisterRequest;
import com.ladharitech.ladhari.entity.user.Role;
import com.ladharitech.ladhari.repository.UserRepository;
import com.ladharitech.ladhari.response.ConnectedUserResponse;
import com.ladharitech.ladhari.service.AuthenticationService;
import com.ladharitech.ladhari.service.UserService;
import com.ladharitech.ladhari.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final SharedMethods sharedMethods;

    public void modifierProfileUser(RegisterRequest request){
        var user=sharedMethods.modifierProfile(request);
        user.setRole(Role.USER);
        userRepository.save(user);
    }


    public void changePassword( ChangePasswordRequest request, Principal connectedUser){

    var user=((User)((UsernamePasswordAuthenticationToken)connectedUser).getPrincipal());
    //check password is correct or not!
        if(!passwordEncoder.matches(request.getCurrentPassword(),user.getPassword())){
            throw new IllegalStateException("Wrong Password!");
        }
        if (!request.getNewPassword().equals(request.getReNewPassword())){
            throw new IllegalStateException("Password not match!");
        }
        //update pass
        user.setPassword(passwordEncoder.encode(request.getReNewPassword()));
        //save new pass
        userRepository.save(user);
    }
}
