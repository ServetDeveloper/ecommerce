package com.example.ecommerce.controller;

import com.example.ecommerce.controllers.UserController;
import com.example.ecommerce.dtos.UserDto;
import com.example.ecommerce.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerUnitTest {

  @Autowired
  MockMvc mvc;

  @MockitoBean
  UserService userService;

  @Test
  void register_valid_user_returns201() throws Exception {
    var body = """
          {"name":"goodUser","email":"good@mail.com","password":"StrongPass123"}
          """;

    when(userService.createUser(any())).thenAnswer(invocation -> {
      var dto = new UserDto();
      dto.setId(40L);
      dto.setName("goodUser");
      dto.setEmail("good@mail.com");
      return dto;
    });

    mvc.perform(post("/users")
      .contentType(MediaType.APPLICATION_JSON)
      .content(body))
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.name").value("goodUser"))
      .andExpect(jsonPath("$.email").value("good@mail.com"));
  }
}
