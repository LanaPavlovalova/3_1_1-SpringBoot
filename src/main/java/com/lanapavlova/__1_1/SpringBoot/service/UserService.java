package com.lanapavlova.__1_1.SpringBoot.service;

import com.lanapavlova.__1_1.SpringBoot.entity.User;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    void save(User user);
    void update(User user);
    void delete(Long id);
}