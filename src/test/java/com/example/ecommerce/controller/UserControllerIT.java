package com.example.ecommerce.controller;

import com.example.ecommerce.controllers.UserController;
import com.example.ecommerce.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerIT {

  @Autowired
  MockMvc mvc;

  @MockitoBean
  private UserService userService;


  @Test
  void register_invalidEmail_returns400() throws Exception{
    var body = """
      {"name":"baddy","email":"qazwsx","password":"Azdzcxv234"}
      """;

    mvc.perform(post("/users")
      .contentType(MediaType.APPLICATION_JSON)
      .content(body))
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$.errors.email").exists());
  }
}
