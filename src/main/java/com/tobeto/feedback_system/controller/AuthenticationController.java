package com.tobeto.feedback_system.controller;

import com.tobeto.feedback_system.core.results.DataResult;
import com.tobeto.feedback_system.core.results.Result;
import com.tobeto.feedback_system.models.concretes.User;
import com.tobeto.feedback_system.payload.requests.SignInRequest;
import com.tobeto.feedback_system.payload.requests.SignUpRequest;
import com.tobeto.feedback_system.payload.responses.JwtAuthResponse;
import com.tobeto.feedback_system.services.concretes.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public Result signup(@Valid @RequestBody SignUpRequest request) throws Exception {
        return authenticationService.signup(request);
    }

    @PostMapping("/signin")
    public JwtAuthResponse signin(@Valid @RequestBody SignInRequest request) throws Exception {
        return authenticationService.signin(request);
    }
}