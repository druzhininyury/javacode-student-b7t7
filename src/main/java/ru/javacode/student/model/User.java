package ru.javacode.student.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import ru.javacode.student.model.view.Views;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView({Views.User.Full.class, Views.User.Short.class, Views.User.Update.class})
    @NotNull(groups = {Views.User.Update.class})
    private Long id;

    @Column(name = "name")
    @JsonView({Views.User.Full.class, Views.User.Short.class, Views.User.New.class, Views.User.Update.class})
    @NotNull(groups = {Views.User.New.class})
    @Length(min = 3, groups = {Views.User.New.class, Views.User.Update.class})
    private String name;

    @Column(name = "email")
    @JsonView({Views.User.Full.class, Views.User.New.class, Views.User.Update.class})
    @NotNull(groups = {Views.User.New.class})
    @Email(groups = {Views.User.New.class, Views.User.Update.class})
    private String email;

    @Column(name = "address")
    @JsonView({Views.User.Full.class, Views.User.New.class, Views.User.Update.class})
    @NotNull(groups = {Views.User.New.class})
    @Length(min = 3, groups = {Views.User.New.class, Views.User.Update.class})
    private String address;

}
