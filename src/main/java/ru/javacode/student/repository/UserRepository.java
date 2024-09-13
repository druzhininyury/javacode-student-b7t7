package ru.javacode.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javacode.student.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
