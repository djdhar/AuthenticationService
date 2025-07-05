package com.generic.auth.controller;

import com.generic.auth.model.User;
import com.generic.auth.service.UserDataFetcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/view")
public class FetchDataAfterLoginController {

    UserDataFetcherService userDataFetcherService;

    public FetchDataAfterLoginController(UserDataFetcherService userDataFetcherService) {
        this.userDataFetcherService = userDataFetcherService;
    }

    @GetMapping("/user")
    public Mono<User> fetchDataAfterLogin(Mono< Authentication > authentication) {
        return userDataFetcherService.fetchDataAfterLogin(authentication);
    }
}
