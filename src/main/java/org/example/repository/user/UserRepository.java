package org.example.repository.user;

import org.example.entity.User;
import org.example.model.Response;

public interface UserRepository {
    Response<User> getUserById(int userId);
    Response<String> getUsernameByID(int id);
    Response<User> getUser(int id, String email);

}
