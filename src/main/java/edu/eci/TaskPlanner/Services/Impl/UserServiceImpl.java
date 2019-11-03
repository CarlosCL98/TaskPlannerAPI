package edu.eci.TaskPlanner.Services.Impl;

import edu.eci.TaskPlanner.Model.User;
import edu.eci.TaskPlanner.Persistence.UserRepository;
import edu.eci.TaskPlanner.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsersList() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(Integer.parseInt(userId));
    }

    @Override
    public User createUser(User user) {
        user.setId((int)(userRepository.count() + 1));
        int userId = user.getId();
        user.setId(userId);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void removeUser(String userId) {
        userRepository.deleteById(Integer.parseInt(userId));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
