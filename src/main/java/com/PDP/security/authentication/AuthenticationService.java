package com.PDP.security.authentication;

import com.PDP.security.JWTService;
import com.PDP.security.user.UserEntity;
import com.PDP.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@AllArgsConstructor
public class AuthenticationService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JWTService jwtService;

    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        String jwt=jwtService.generateToken(new HashMap<>(),user);
        AuthenticationResponse response=new AuthenticationResponse();
        response.setToken(jwt);
        response.setRole(user.getRole());
        return response;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(),request.getPassword()));
        var user=userRepository.findByName(request.getLogin()).orElseThrow();
        String jwt=jwtService.generateToken(new HashMap<>(),user);

        AuthenticationResponse response=new AuthenticationResponse();
        response.setToken(jwt);
        response.setRole(user.getRole());
        return response;
    }
}
