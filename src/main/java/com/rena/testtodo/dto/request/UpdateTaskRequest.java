package com.rena.testtodo.dto.request;

import com.rena.testtodo.enums.Category;
import com.rena.testtodo.enums.Priority;
import com.rena.testtodo.enums.Status;
import lombok.Data;

@Data
public class UpdateTaskRequest {
    private String title;
    private String description;
    private Category category;
    private Priority priority;
    private Status status;
}
