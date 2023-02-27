package com.example.TimeApp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomePage {
    @GetMapping("")
    public String home(){
        return "Customers/Home/homepage";
    }

}
