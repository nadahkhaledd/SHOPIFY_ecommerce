package org.example.repository.user;

import org.example.entity.User;
import org.example.model.Response;

public interface UserRepository {
    User getUserById(int userId);
    Response<User> getUser(int id, String email);

}
