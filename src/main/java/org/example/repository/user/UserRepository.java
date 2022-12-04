package org.example.repository.user;

import org.example.entity.User;

public interface UserRepository {
    User getUserById(int userId);

}
