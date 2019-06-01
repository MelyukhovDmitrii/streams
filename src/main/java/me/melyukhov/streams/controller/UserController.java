package me.melyukhov.streams.controller;

import me.melyukhov.streams.model.dto.LoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping(path = "/login")
    public String login(LoginDTO loginDTO){
        System.out.println(loginDTO.getUsername());
        System.out.println(loginDTO.getPassword());
        return "login";
    }

    @PostMapping(path = "/register")
    public String register(){
        return "register";
    }

}
