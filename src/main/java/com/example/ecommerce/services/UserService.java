package com.example.ecommerce.services;

import com.example.ecommerce.dtos.ChangePasswordRequest;
import com.example.ecommerce.dtos.RegisterUserRequest;
import com.example.ecommerce.dtos.UpdateUserRequest;
import com.example.ecommerce.dtos.UserDto;
import com.example.ecommerce.entities.User;
import com.example.ecommerce.exception.InvalidPasswordException;
import com.example.ecommerce.mappers.UserMapper;
import com.example.ecommerce.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public Optional<UserDto> getUserById(Long id) {
    return userRepository.findById(id)
      .map(userMapper::toUserDto);
  }

  public List<UserDto> getAllUsers(String sort) {
    if (!Set.of("name", "email", "phoneNumber").contains(sort)) sort = "name";
    return userRepository.findAll(Sort.by(sort))
      .stream()
      .map(userMapper::toUserDto)
      .toList();
  }

  public UserDto createUser(RegisterUserRequest request) {
    User user = userMapper.toUserEntity(request);

    userRepository.save(user);

    return userMapper.toUserDto(user);
  }

  public UserDto updateUser(Long id, UpdateUserRequest request) {
    User user = userRepository.findById(id).orElseThrow(
      () -> new EntityNotFoundException("User not found with this id: " + id));

    userMapper.update(request, user);

    userRepository.save(user);

    return userMapper.toUserDto(user);
  }

  public UserDto deleteUser(Long id) {
    User user = userRepository.findById(id).orElseThrow(
      () -> new EntityNotFoundException("User not found with this id: " + id)
    );
    userRepository.deleteById(id);

    return userMapper.toUserDto(user);
  }

  @Transactional
  public void changePassword(Long id, ChangePasswordRequest request) {
    User user = userRepository.findById(id).orElseThrow(
      ( () -> new EntityNotFoundException("User not found with this id: " + id))
    );

    if (!user.getPassword().equals(request.getOldPassword())) {
      throw new InvalidPasswordException("Old password is incorrect");
    }

    user.changePassword(request.getNewPassword());

    userRepository.save(user);

  }

  public boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

}
