package com.ladharitech.ladhari.service;

import com.ladharitech.ladhari.dto.ChangePasswordRequest;
import com.ladharitech.ladhari.dto.RegisterRequest;
import com.ladharitech.ladhari.response.ConnectedUserResponse;

import java.security.Principal;

public interface UserService {
    void changePassword( ChangePasswordRequest request ,Principal connectedUser);
    void modifierProfileUser(RegisterRequest request);
}
