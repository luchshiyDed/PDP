package com.PDP.security.authentication;

import com.PDP.security.user.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    @Autowired
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserEntity request){
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        authenticationService.deleteUser(id);
    }
    @GetMapping("/users")
    public Iterable<UserEntity> getAll(){
        return authenticationService.getAll();
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
