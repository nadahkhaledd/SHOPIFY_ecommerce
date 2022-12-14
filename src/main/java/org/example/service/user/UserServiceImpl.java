package org.example.service.user;

import org.example.entity.User;
import org.example.model.Response;
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
    public Response<User> getUserById(int userId) {
        if(userId == -1)
            throw new IllegalArgumentException();

        return userRepository.getUserById(userId);
    }

    @Override
    public Response<User> getUserByEmail(String email) {
        if(email.isBlank())
            throw new IllegalArgumentException();
        if(email == null)
            throw new NullPointerException();

        return userRepository.getUserByEmail(email);
    }
}
