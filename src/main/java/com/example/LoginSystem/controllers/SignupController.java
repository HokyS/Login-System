package com.example.LoginSystem.controllers;

import com.example.LoginSystem.entities.SignupActivationDTO;
import com.example.LoginSystem.entities.SignupDTO;
import com.example.LoginSystem.services.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class SignupController {

    @Autowired
    private SignupService signupService;

    @PostMapping("/signup")
    public void signup(@RequestBody SignupDTO signupDTO) throws Exception{
        signupService.signup(signupDTO);
    }

    @PostMapping("/signup/activation")
    public void activation(@RequestBody SignupActivationDTO signupActivationDTO) throws Exception{
        signupService.activate(signupActivationDTO);
    }
}
