package com.generic.auth.service;

import com.generic.auth.model.User;
import com.generic.auth.repository.UserRepository;
import com.generic.auth.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserDataFetcherService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public Mono<User> fetchDataAfterLogin(Mono<Authentication> authentication) {
        return authentication.map(Authentication::getName)
                .flatMap(userRepository::findByEmail)
                .switchIfEmpty(Mono.error(new RuntimeException("No user available!")))
                .onErrorResume(error -> Mono.error(new RuntimeException("Error while fetching data!")));
    }
}
