package com.rena.testtodo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse <T> {
    private String message;
    private T data;
    private int statusCode;

    public  GenericResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
