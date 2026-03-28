package com.telusko.spring_boot_rest.service;

import com.telusko.spring_boot_rest.model.User;
import com.telusko.spring_boot_rest.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User saveUser(User user) {
        // 이제 본인의 엔티티이므로 setPassword 호출이 가능합니다 (Lombok @Data 혹은 Setter 필요)
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }
}