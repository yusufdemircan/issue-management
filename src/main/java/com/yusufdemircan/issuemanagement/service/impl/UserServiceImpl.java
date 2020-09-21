package com.yusufdemircan.issuemanagement.service.impl;

import com.yusufdemircan.issuemanagement.entity.User;
import com.yusufdemircan.issuemanagement.repository.UserRepository;

import com.yusufdemircan.issuemanagement.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        if (user.getEmail() == null){
            throw  new IllegalArgumentException("Email cannot be null");
        }

        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public Page<User> getAllPageble(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public Boolean delete(User user) {

        userRepository.delete(user);
        return null;
    }
}
