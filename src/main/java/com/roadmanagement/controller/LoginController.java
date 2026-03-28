package com.roadmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

@Controller
public class LoginController {

    // 👇 This handles "/"
    @GetMapping("/")
    public String root(OAuth2AuthenticationToken auth) {
        if (auth == null) {
            return "login"; // show login page
        }
        return "redirect:/index.html"; // after login → go to static page
    }

    // 👇 This handles "/login"
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}