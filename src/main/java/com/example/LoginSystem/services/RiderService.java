package com.example.LoginSystem.services;

import com.example.LoginSystem.entities.User;
import com.example.LoginSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RiderService {

    @Autowired
    private UserRepository userRepository;

    public User pickRider(){
        if(userRepository.pickRider().isPresent()){
            return userRepository.pickRider().get();
        }else{
            return userRepository.findAll(PageRequest.of(0,1)).toList().get(0);
        }
    }
}