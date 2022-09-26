package com.example.Addon.AddonWeb.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SealedObject;

@Component
@Getter
public class ServiceRegistry {

    @Autowired
    private UserService userService;
    

}
