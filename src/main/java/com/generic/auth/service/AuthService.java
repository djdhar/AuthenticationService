package com.generic.auth.service;

import com.generic.auth.dto.SignupRequest;
import com.generic.auth.model.User;
import com.generic.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Mono<Object> signup(SignupRequest request) {
        return userRepository.findByEmail(request.getEmail())
                .flatMap(existingUser -> Mono.error(new RuntimeException("Email already exists")))
                .switchIfEmpty(Mono.defer(() -> {
                    User user = new User();
                    user.setEmail(request.getEmail());
                    user.setName(request.getName());
                    user.setPassword(passwordEncoder.encode(request.getPassword()));
                    return userRepository.save(user);
                }));
    }
}
