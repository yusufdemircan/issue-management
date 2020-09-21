package com.yusufdemircan.issuemanagement.service;



import com.yusufdemircan.issuemanagement.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {

    User save(User user);

    User getById(Long id);

    Page<User> getAllPageble(Pageable pageable);

    User getByUsername(String username);

    Boolean delete(User user);
}
