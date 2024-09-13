package ru.javacode.student.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javacode.student.model.User;
import ru.javacode.student.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User with id=" + userId + " doesn't exist.");
        }
        return userOptional.get();
    }

    @Override
    @Transactional
    public User addUser(User user) {
        User dbUser = userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        Optional<User> userOptional = userRepository.findById(user.getId());
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User with id=" + user.getId() + " doesn't exist.");
        }
        User dbUser = userOptional.get();

        if (user.getName() != null) {
            dbUser.setName(user.getName());
        }
        if (user.getEmail() != null) {
            dbUser.setEmail(user.getEmail());
        }
        if (user.getAddress() != null) {
            dbUser.setAddress(user.getAddress());
        }

        dbUser = userRepository.save(dbUser);

        return dbUser;
    }

}
