package com.rena.testtodo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rena.testtodo.persitance.entity.Task;
import com.rena.testtodo.persitance.entity.Users;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private String firstName;
    private String lastName;
    private String email;
    private List<Task> tasks = new ArrayList<>();

    public static UserResponse FromNoTaskEntity(Users user) {
        UserResponse response = new UserResponse();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        return response;
    }

    public static UserResponse fromEntity(Users user) {
        UserResponse response = FromNoTaskEntity(user);
        response.setTasks(user.getTask());
        return response;
    }
}
