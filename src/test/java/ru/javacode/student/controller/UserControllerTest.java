package ru.javacode.student.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.javacode.student.model.User;
import ru.javacode.student.service.UserService;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    MockMvc mvc;

    @MockBean
    UserService userService;

    @Test
    public void getUserByIdTest_whenInputValid_thenUserReturned() throws Exception {
        long userId = 1L;
        User dbUser = User.builder().id(userId).name("Vladimir").email("vladimir@mail.com").address("Moscow, 1").build();

        when(userService.getUser(userId)).thenReturn(dbUser);

        mvc.perform(get("/users/{userId}", userId)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(userId), Long.class))
                .andExpect(jsonPath("$.name", is(dbUser.getName())))
                .andExpect(jsonPath("$.email", is(dbUser.getEmail())))
                .andExpect(jsonPath("$.address", is(dbUser.getAddress())));
    }

    @Test
    public void getUserByIdTest_whenUserIdInvalid_thenExceptionThrown() throws Exception {
        long userId = 2L;

        when(userService.getUser(userId)).thenThrow(new EntityNotFoundException("Error"));

        mvc.perform(get("/users/{userId}", userId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addUser_whenInputValid_thenAddedUserReturned() throws Exception {
        long userId = 1L;
        String requestBody = """
                {
                  "name": "Vladimir",
                  "email": "vladimir@mail.com",
                  "address": "Moscow, 1"
                }""";

        User dbUser = User.builder().id(userId).name("Vladimir").email("vladimir@mail.com").address("Moscow, 1").build();

        when(userService.addUser(any(User.class))).thenReturn(dbUser);

        mvc.perform(post("/users")
                        .content(requestBody)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void addUser_whenNameInvalid_thenExceptionThrown() throws Exception {
        long userId = 1L;
        String requestBody = """
                {
                  "name": "Vl",
                  "email": "vladimir@mail.com",
                  "address": "Moscow, 1"
                }""";

        mvc.perform(post("/users")
                        .content(requestBody)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUser_whenInputValid_thenUpdatedUserReturned() throws Exception {
        long userId = 1L;
        String requestBody = """
                {
                  "id" : 1,
                  "name": "Vovchik"
                }""";

        User dbUser = User.builder().id(userId).name("Vovchik").email("vladimir@mail.com").address("Moscow, 1").build();

        when(userService.updateUser(any(User.class))).thenReturn(dbUser);

        mvc.perform(patch("/users")
                        .content(requestBody)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(userId), Long.class))
                .andExpect(jsonPath("$.name", is(dbUser.getName())))
                .andExpect(jsonPath("$.email", is(dbUser.getEmail())))
                .andExpect(jsonPath("$.address", is(dbUser.getAddress())));;
    }

}
