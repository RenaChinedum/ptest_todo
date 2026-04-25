package com.rena.testtodo.service;

import com.rena.testtodo.dto.request.CreateUserRequest;
import com.rena.testtodo.dto.response.GenericResponse;
import com.rena.testtodo.dto.response.UserResponse;
import com.rena.testtodo.enums.StatusCode;
import com.rena.testtodo.persitance.entity.Users;
import com.rena.testtodo.persitance.repository.UserRepository;
import org.springframework.stereotype.Service;

import static com.rena.testtodo.util.ConstantMessage.EMAIL_ALREADY_EXISTING;
import static com.rena.testtodo.util.ConstantMessage.USER_CREATED_SUCCESSFULLY;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public GenericResponse<UserResponse> createUser(CreateUserRequest request) {
        validateUserEmail(request.email());
        Users newUser = setUser(request);
        return new GenericResponse<>(USER_CREATED_SUCCESSFULLY, UserResponse.FromNoTaskEntity(newUser), StatusCode.SUCCESS_CODE.getCode());
    }

    private Users setUser(CreateUserRequest createUserRequest) {
        return userRepository.save(Users.builder()
                .firstName(createUserRequest.firstName())
                .lastName(createUserRequest.lastName())
                .email(createUserRequest.email())
                .build());
    }

    private void validateUserEmail(String email) {
        Users existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            throw new RuntimeException(String.format(EMAIL_ALREADY_EXISTING, email));
        }
    }
}
