package com.bumblebee.users.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/login")
    public String login() {
        return "/login OK";
    }

    @GetMapping("/registration")
    public String registration() {
        return "/registration OK";
    }

}
