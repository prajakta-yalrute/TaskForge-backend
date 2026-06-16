package com.taskforge.backend.service;

import com.taskforge.backend.dto.LoginRequest;
import com.taskforge.backend.dto.RegisterRequest;
import com.taskforge.backend.entity.Role;
import com.taskforge.backend.entity.User;
import com.taskforge.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtService jwtService;

    public String register(RegisterRequest request) {

        if (userRepository.findByEmail(
                request.getEmail()).isPresent()) {

            throw new RuntimeException(
                    "Email already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(
                        encoder.encode(
                                request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return "User Registered Successfully";
    }

    public String login(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(
                        () -> new RuntimeException(
                                "User not found"));

        if (!encoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException(
                    "Invalid Password");
        }

        return jwtService.generateToken(
                user.getEmail());
    }
}