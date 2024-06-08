package com.example.cw_spring.controller;

import com.example.cw_spring.reqAndres.response.JwtAuthResponse;
import com.example.cw_spring.reqAndres.secure.SignIn;
import com.example.cw_spring.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final AuthenticationService authenticationService;

    @GetMapping("/health")
    public String healthTest() {
        return "Health check passed";
    }

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthResponse> signUp() {
        return ResponseEntity.ok(authenticationService.signUp());
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SignIn signInReq) {
        return ResponseEntity.ok(authenticationService.signIn(signInReq));
    }
}
