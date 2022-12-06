package org.example.service.user;

import org.example.entity.User;
import org.example.model.Response;

public interface UserService {
    Response<User> getUserById(int userId);

    Response<User> getUser(int userId, String email);
}
