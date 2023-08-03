package com.digitalemre.springsecurityjwt.service;

import com.digitalemre.springsecurityjwt.dto.UserDto;
import com.digitalemre.springsecurityjwt.dto.UserRequest;
import com.digitalemre.springsecurityjwt.dto.UserResponse;
import com.digitalemre.springsecurityjwt.entity.User;
import com.digitalemre.springsecurityjwt.enums.Role;
import com.digitalemre.springsecurityjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public UserResponse save(UserDto userDto) {
        User user = User.builder()
                .nameSurname(userDto.getNameSurname())
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

        var token = jwtService.generateToken(user);

        return UserResponse.builder()
                .token(token)
                .build();
    }

    public UserResponse auth(UserRequest userRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
        User user = userRepository.findByUsername(userRequest.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);
        return UserResponse.builder()
                .token(token)
                .build();
    }
}
