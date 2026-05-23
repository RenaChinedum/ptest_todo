package com.rena.testtodo.service;

import com.rena.testtodo.dto.request.CreateUserRequest;
import com.rena.testtodo.dto.request.UpdateUserRequest;
import com.rena.testtodo.dto.response.GenericResponse;
import com.rena.testtodo.dto.response.UserResponse;
import com.rena.testtodo.enums.StatusCode;
import com.rena.testtodo.persitance.entity.Users;
import com.rena.testtodo.persitance.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.rena.testtodo.util.ConstantMessage.EMAIL_ALREADY_EXISTING;
import static com.rena.testtodo.util.ConstantMessage.USER_CREATED_SUCCESSFULLY;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public GenericResponse<UserResponse> createUser(CreateUserRequest request) {
        validateUserEmail(request.email());
        Users newUser = setUser(request);
        return new GenericResponse<>(USER_CREATED_SUCCESSFULLY, UserResponse.FromNoTaskEntity(newUser), StatusCode.SUCCESS_CODE.getCode());
    }


    public Optional<GenericResponse<UserResponse>> getUserById(Long id) {
        return userRepository.findById(id)
                .map(u -> new GenericResponse<>("User Retrieved Successfully",
                        UserResponse.fromEntity(u), StatusCode.SUCCESS_CODE.getCode()));
    }

    public Optional<GenericResponse<UserResponse>> getUserById2(Long id) {
        Users users = findUserById(id);
        return Optional.of(new GenericResponse<>("User Retrieved Successfully",
                UserResponse.fromEntity(users), StatusCode.SUCCESS_CODE.getCode()));
    }

    public Optional<GenericResponse<UserResponse>> getUserById3(Long id) {
        return Optional.of(new GenericResponse<>("User Retrieved Successfully",
                UserResponse.fromEntity(findUserById(id)),
                StatusCode.SUCCESS_CODE.getCode()));
    }

    public GenericResponse<UserResponse> updateUser(Long id, UpdateUserRequest request) {
        Users user = findUserById(id);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        userRepository.save(user);
        return new GenericResponse<>("User Updated Successfully", UserResponse.fromEntity(user), StatusCode.SUCCESS_CODE.getCode());
    }

    public GenericResponse<?> deleteUser(Long id) {
        Users user = findUserById(id);
        userRepository.delete(user);
        return new GenericResponse<>("User Deleted Successfully", StatusCode.SUCCESS_CODE.getCode());
    }

    private Users findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()
                -> new RuntimeException(String.format("User with id %d not found", id)));
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
