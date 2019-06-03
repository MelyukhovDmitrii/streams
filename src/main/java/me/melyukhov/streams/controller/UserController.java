package me.melyukhov.streams.controller;

import me.melyukhov.streams.model.dao.UserInfo;
import me.melyukhov.streams.model.dto.LoginDTO;
import me.melyukhov.streams.model.dto.RegisterDTO;
import me.melyukhov.streams.model.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

//@Controller
public class UserController {

//    UserInfoRepository userInfoRepository;
//
//    BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Autowired
//    public UserController(UserInfoRepository userInfoRepository){
//        this.userInfoRepository = userInfoRepository;
//        bCryptPasswordEncoder = new BCryptPasswordEncoder();
//    }
//
//    @PostMapping(path = "/register")
//    public String register(RegisterDTO registerDTO){
//        if(userInfoRepository.findByLogin(registerDTO.getUsername()).isPresent()){
//            return "login?error";
//        } else {
//            UserInfo userInfo = new UserInfo();
//            userInfo.setEmail(registerDTO.getEmail());
//
//            String password = bCryptPasswordEncoder.encode(registerDTO.getPassword());
//            userInfo.setPassword(password);
//
//            userInfo.setLogin(registerDTO.getUsername());
//            userInfo.setDateRegistration(new Date());
//            userInfoRepository.save(userInfo);
//            return "login";
//        }
//    }

}
