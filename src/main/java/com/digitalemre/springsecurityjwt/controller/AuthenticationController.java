package com.digitalemre.springsecurityjwt.controller;

import com.digitalemre.springsecurityjwt.dto.UserDto;
import com.digitalemre.springsecurityjwt.dto.UserRequest;
import com.digitalemre.springsecurityjwt.dto.UserResponse;
import com.digitalemre.springsecurityjwt.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/save")
    public ResponseEntity<UserResponse> save(@RequestBody UserDto userDto){
        return ResponseEntity.ok(authenticationService.save(userDto));
    }

    @PostMapping("/auth")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(authenticationService.auth(userRequest));
    }

}
