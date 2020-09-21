package com.yusufdemircan.issuemanagement.repository;


import com.yusufdemircan.issuemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User getByUsername(String username);
}
