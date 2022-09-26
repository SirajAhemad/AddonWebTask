package com.example.Addon.AddonWeb.serviceImpl;

import com.example.Addon.AddonWeb.entity.User;
import com.example.Addon.AddonWeb.repository.UserRepository;
import com.example.Addon.AddonWeb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean saveUser(User user) {

        User savedUser = userRepository.save(user);

        if (savedUser != null)
            return true;
        else
            return false;
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


}
