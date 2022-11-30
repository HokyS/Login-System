package com.example.LoginSystem.controllers;

import com.example.LoginSystem.entities.LoginDTO;
import com.example.LoginSystem.entities.LoginRTO;
import com.example.LoginSystem.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public LoginRTO login(@RequestBody LoginDTO loginDTO) throws Exception {
        LoginRTO loginRTO = loginService.login(loginDTO);
        if(loginDTO == null) throw new Exception("Cannot login");
        return loginRTO;
    }

}