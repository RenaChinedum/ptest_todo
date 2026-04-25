package com.rena.testtodo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record CreateUserRequest(
   @NotEmpty(message = "First name is required") String firstName,
   @NotEmpty(message = "Last name is required") String lastName,
   @NotEmpty(message = "Email is required") @Email(message = "Enter valid email") String email) {
}
