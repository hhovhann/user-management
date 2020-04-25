package com.hhovhann.usermanagement.services;

import com.hhovhann.usermanagement.entities.User;
import com.hhovhann.usermanagement.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    
    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserByUserId(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void deleteUserById(Long userId) {
         userRepository.deleteById(userId);
    }

    @Override
    public Optional<Void> updateUserById(Long userId) {
        return null;
    }
}
