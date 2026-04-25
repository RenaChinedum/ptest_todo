package com.rena.testtodo.controller;

import com.rena.testtodo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/home")
@RequiredArgsConstructor
public class HomeClass {

    private final UserService userService;

    @GetMapping("/welcome")
    public String home(){
        return "Welcome to Home Class";
    }
}
