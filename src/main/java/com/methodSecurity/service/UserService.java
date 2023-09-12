package com.methodSecurity.service;

import com.methodSecurity.entity.UserInfo;
import com.methodSecurity.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserInfoRepository repository;

    public String addUser(UserInfo userInfo) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Optional<UserInfo> byEmail = repository.findByEmail(userInfo.getEmail());
        if (byEmail.isPresent()) {
            return "user is already present :: ";
        } else {
            userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
            repository.save(userInfo);
            return "user added to db ";
        }
    }

}
