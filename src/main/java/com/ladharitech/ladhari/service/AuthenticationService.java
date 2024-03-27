package com.ladharitech.ladhari.service;

import com.ladharitech.ladhari.dto.ChangePasswordRequest;
import com.ladharitech.ladhari.dto.LoginRequest;
import com.ladharitech.ladhari.dto.RegisterRequest;
import com.ladharitech.ladhari.entity.user.User;
import com.ladharitech.ladhari.response.AuthenticationResponse;
import com.ladharitech.ladhari.response.ConnectedUserResponse;
import com.ladharitech.ladhari.response.Response;

import java.security.Principal;

public interface AuthenticationService {
     AuthenticationResponse register(RegisterRequest request);
     AuthenticationResponse login(LoginRequest request);

     AuthenticationResponse confirm(String token);
     ConnectedUserResponse userConnected();
     AuthenticationResponse reSend();
     Response registeradmin(RegisterRequest request);
}
