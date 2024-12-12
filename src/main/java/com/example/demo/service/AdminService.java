package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {
    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void reportUsers(List<Long> userIds) {
        List<User> allById = userRepository.findAllById(userIds);
        if (allById.isEmpty()) {
            throw new IllegalArgumentException("해당 ID에 맞는 값이 존재하지 않습니다.");
        }
        for (User user : allById) {
            user.updateStatusToBlocked();
        }
    }
}
