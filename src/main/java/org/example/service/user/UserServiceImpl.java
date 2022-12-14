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

        Response userResponse =  userRepository.getUserById(userId);
        if(userResponse.getObjectToBeReturned() == null)
            return new Response<>("no content", 204, false, true, null);

        return userResponse;
    }

    @Override
    public Response<User> getUserByEmail(String email) {
        if(email.isBlank())
            throw new IllegalArgumentException();
        if(email == null)
            throw new NullPointerException();

        Response userResponse =  userRepository.getUserByEmail(email);
        if(userResponse.getObjectToBeReturned() == null)
            return new Response<>("no content", 204, false, true, null);

        return userResponse;
    }
}
