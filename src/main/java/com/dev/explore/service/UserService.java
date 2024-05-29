package com.dev.explore.service;

import com.dev.explore.dto.MottoPassDto;
import com.dev.explore.dto.UserDto;
import com.dev.explore.entity.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {

    Page<User> getAllUsers(int pageNumber, int pageSize);

    Page<User> getALLAdmins(int pageNumber, int pageSize);

    Optional<User> getUser(String id);

    void addUser(UserDto userDto);

    void registerUser(UserDto userDto);

    User updateUpdate(User user, String id);

    void deleteUser(String id);

    User findByEmail(String email);

    String resetPassword(MottoPassDto passwordDto);
}
