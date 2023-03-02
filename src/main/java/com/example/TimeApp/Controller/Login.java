package com.example.TimeApp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Login {
    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
