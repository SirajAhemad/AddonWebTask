package com.example.Addon.AddonWeb.service;

import com.example.Addon.AddonWeb.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Boolean saveUser(User user);

    void deleteById(Integer id);

    Optional<User> findById(Integer id);

    List<User> findAll();

}
