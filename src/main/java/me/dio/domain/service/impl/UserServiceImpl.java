package me.dio.domain.service.impl;

import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.domain.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userToCreate) {
        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw new IllegalArgumentException("This Account number already exists.");
        }
        return userRepository.save(userToCreate);
    }

    @Override
    public List<User> findAllUsers() {
        if(userRepository.findAll().isEmpty()) {
            throw new NoSuchElementException("No Users found! Try to insert a User");
        }
        return userRepository.findAll();
    }

    @Override
    public void deleteByid(Long id) {
        if (!userRepository.existsById(id)){
            throw new NoSuchElementException("This User doesn't exist.");
        }
        userRepository.deleteById(id);
    }

}
