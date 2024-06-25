package br.com.leobrambilla.find_jobs.services;

import br.com.leobrambilla.find_jobs.controllers.UserController;
import br.com.leobrambilla.find_jobs.entities.User;
import br.com.leobrambilla.find_jobs.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        logger.info("Find all users");
        return userRepository.findAll();
    }

    public User findById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(UUID id, User updateUser) {
        return userRepository.findById(id).map(user ->{
            user.setUsername(updateUser.getUsername());
            user.setPassword(updateUser.getPassword());
            return userRepository.save(user);
        }).orElse(null);
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    public void addRoleToUser(UUID id, String role) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.getRoles().add(role);
        userRepository.save(user);
    }

}

