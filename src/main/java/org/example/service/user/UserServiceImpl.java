package org.example.service.user;

import org.example.entity.User;
import org.example.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public User getUserById(int userId) {
        return userRepository.getUserById(userId);
    }
}