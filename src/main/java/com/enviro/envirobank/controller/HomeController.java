package com.enviro.envirobank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/banking")
public class HomeController {
@GetMapping("/welcome")
    public String home(){
    return "resetPassword";
}
}
