package com.yusufdemircan.issuemanagement.service;



import com.yusufdemircan.issuemanagement.dto.UserDto;
import com.yusufdemircan.issuemanagement.entity.User;
import com.yusufdemircan.issuemanagement.util.TPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {

    UserDto save(UserDto user);


    UserDto getById(Long id);

    TPage<UserDto> getAllPageble(Pageable pageable);

    User getByUsername(String username);

    Boolean delete(Long id);
}
