package com.rena.testtodo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rena.testtodo.enums.Category;
import com.rena.testtodo.enums.Priority;
import com.rena.testtodo.enums.Status;
import com.rena.testtodo.persitance.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskResponse {
    private Long Id;
    private String title;
    private String description;
    private Category category;
    private Priority priority;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;

    public static TaskResponse fromEntity(Task task) {
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setCategory(task.getCategory());
        response.setPriority(task.getPriority());
        response.setStatus(task.getStatus());
        response.setCreatedAt(task.getCreatedAt());
        response.setUpdatedAt(task.getUpdatedAt());
        response.setUserId(task.getUser() != null ? task.getUser().getId() : null);
        return response;
    }
}
