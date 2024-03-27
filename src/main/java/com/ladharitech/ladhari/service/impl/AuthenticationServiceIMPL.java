package com.ladharitech.ladhari.service.impl;

import com.ladharitech.ladhari.dto.LoginRequest;
import com.ladharitech.ladhari.dto.RegisterRequest;
import com.ladharitech.ladhari.entity.token.ConfirmationEmailToken;
import com.ladharitech.ladhari.repository.ConfirmationTokenRepository;
import com.ladharitech.ladhari.repository.TokenRepository;
import com.ladharitech.ladhari.entity.token.Token;
import com.ladharitech.ladhari.entity.token.TokenType;
import com.ladharitech.ladhari.entity.user.Role;
import com.ladharitech.ladhari.entity.user.User;
import com.ladharitech.ladhari.repository.UserRepository;
import com.ladharitech.ladhari.response.AuthenticationResponse;
import com.ladharitech.ladhari.response.ConnectedUserResponse;
import com.ladharitech.ladhari.response.Response;
import com.ladharitech.ladhari.service.AuthenticationService;
import com.ladharitech.ladhari.service.EmailService;
import com.ladharitech.ladhari.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.Principal;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceIMPL implements AuthenticationService {

    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final ConfirmationTokenRepository confirmationTokenRepository;

    private final EmailService emailService;


    //login
    public AuthenticationResponse login(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken= jwtService.generateToken(user);
        revokeAllUserTokens(user);
        savedUserToken(user,jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }



    //confirm account
    @Override
    public AuthenticationResponse confirm(String token) {
        ConfirmationEmailToken verifiedToken = confirmationTokenRepository.findByConfirmationToken(token);
        if (verifiedToken!= null && verifiedToken.getValid()){
            //update user and save it in db
             User user= userRepository.findByEmail(verifiedToken.getUser().getEmail()).orElseThrow();
             user.setEnabled(true);
             userRepository.save(user);
             //update verified tokens to false state save it in the db
             verifiedToken.setValid(false);
             confirmationTokenRepository.save(verifiedToken);
             return AuthenticationResponse.builder()
                     .token("Compte Vérifiée!")
                     .build();
        }
        return AuthenticationResponse.builder()
                .token("The link is invalid or broken!")
                .build();
    }


    //resend confirmation account
    public AuthenticationResponse reSend(){
        if (userConnected().isConnected()){
            User user=userRepository.findByEmail(userConnected().getUser().getEmail()).orElseThrow();
            revokeAllValidConfirmationEmailToken(user);
            var reToken=saveRegistrationTokenAndSendEmail(user);
            return AuthenticationResponse.builder()
                    .token(reToken)
                    .build();
        }
        return AuthenticationResponse.builder().token("Not connected! ").build();
    }


    //get connected user
    @Override
    public ConnectedUserResponse userConnected() {
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            return ConnectedUserResponse.builder().isConnected(true).user(user).build();
        } else {
            return ConnectedUserResponse.builder().isConnected(false).user(null).build();
        }
    }

    //create admin accounts
    public Response registeradmin(RegisterRequest request){
        var user= User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .isEnabled(true)
                .build();
        userRepository.save(user);
        //savedUserToken(savedUser,jwtToken);
        return Response.builder()
                .message("Admin "+ user.getFirstname()+" "+user.getLastname()+" Created!")
                .build();
    }
    //register
    public AuthenticationResponse register(RegisterRequest request){
        var user= User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        var savedUser= userRepository.save(user);
        var jwtToken= saveRegistrationTokenAndSendEmail(savedUser);
        //savedUserToken(savedUser,jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }


    private String saveRegistrationTokenAndSendEmail(User user){
        if (!user.getIsEnabled()){
            ConfirmationEmailToken confirmationToken = new ConfirmationEmailToken(user);
            confirmationTokenRepository.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("***************************");
            mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:3000/api/v1/auth/confirm-account?token="+confirmationToken.getConfirmationToken());
            emailService.sendEmail(mailMessage);
            return confirmationToken.getConfirmationToken();
        }
        return "Your account already enabled!";
    }

    private void revokeAllUserTokens(User user){
        var validTokens=tokenRepository.findAllValidTokensByUsers(user.getId());
        if (validTokens.isEmpty()){
            return;
        }
        validTokens.forEach(t->{
           t.setExpired(true);
           t.setRevoked(true);
        });
        tokenRepository.saveAll(validTokens);
    }

    private void savedUserToken(User savedUser,String jwtToken){
        var token= Token.builder()
                .user(savedUser)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }

    //update all previous tokens
    private void revokeAllValidConfirmationEmailToken(User user){
        var validConfirmationEmailTokens=confirmationTokenRepository.findAllConfirmationTokensByUser(user.getId());
        if (validConfirmationEmailTokens.isEmpty()){
            return;
        }
        validConfirmationEmailTokens.forEach(t->{
            t.setValid(false);
        });
        confirmationTokenRepository.saveAll(validConfirmationEmailTokens);
    }
}
