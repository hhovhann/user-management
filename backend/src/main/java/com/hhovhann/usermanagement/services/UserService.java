package com.hhovhann.usermanagement.services;

import com.hhovhann.usermanagement.entities.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User addUser(User user);

    List<User> getUsers();

    Optional<User> findUserByUserId(Long userId);

    void deleteUserById(Long userId);

    Optional<Void> updateUserById(Long userId);
}
