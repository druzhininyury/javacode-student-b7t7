package ru.javacode.student.service;

import ru.javacode.student.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUser(Long userId);

    User addUser(User user);

    User updateUser(User user);

}
