package com.jeuxolympiques.jo2024.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.jeuxolympiques.jo2024.model.User;
import com.jeuxolympiques.jo2024.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }
}
