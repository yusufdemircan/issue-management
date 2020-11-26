package com.yusufdemircan.issuemanagement.service.impl;

import com.yusufdemircan.issuemanagement.dto.ProjectDto;
import com.yusufdemircan.issuemanagement.dto.RegistrationRequest;
import com.yusufdemircan.issuemanagement.dto.UserDto;
import com.yusufdemircan.issuemanagement.entity.Project;
import com.yusufdemircan.issuemanagement.entity.User;
import com.yusufdemircan.issuemanagement.repository.UserRepository;

import com.yusufdemircan.issuemanagement.service.UserService;
import com.yusufdemircan.issuemanagement.util.TPage;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDto save(UserDto user) {
        if (user.getNameSurname() == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }

        User u = modelMapper.map(user, User.class);
        u = userRepository.save(u);
        user.setId(u.getId());
        return user;
    }


    @Override
    public UserDto getById(Long id) {

        User dtos = userRepository.getOne(id);

        return modelMapper.map(dtos, UserDto.class);


    }

    public User getById2(Long id) {

        User dtos = userRepository.getOne(id);

        return dtos;


    }

    @Override
    public TPage<UserDto> getAllPageble(Pageable pageable) {
        Page<User> data = userRepository.findAll(pageable);
        TPage<UserDto> response = new TPage<>();
        UserDto[] dtos = modelMapper.map(data.getContent(), UserDto[].class);
        response.setStat(data, Arrays.asList(dtos));
        return response;


    }

    public List<UserDto> getAll() {
        List<User> data = userRepository.findAll();
        return Arrays.asList(modelMapper.map(data, UserDto[].class));
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public Boolean delete(Long id) {

        userRepository.deleteById(id);
        return null;
    }
    @Transactional
    public Boolean register(RegistrationRequest registrationRequest) {
       try {
           User user = new User();
           user.setEmail(registrationRequest.getEmail());
           user.setNameSurname(registrationRequest.getNameSurname());
           user.setUsername(registrationRequest.getUsername());
           user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
           userRepository.save(user);
           return Boolean.TRUE;
       }catch (Exception e){
                log.error("Registeration=>",e);
                return Boolean.FALSE;
       }


    }
}
