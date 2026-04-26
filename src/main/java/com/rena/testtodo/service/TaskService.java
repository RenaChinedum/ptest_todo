package com.rena.testtodo.service;

import com.rena.testtodo.exception.TestTodoException;
import com.rena.testtodo.persitance.entity.Task;
import com.rena.testtodo.persitance.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    //TODO: Implement create, update, delete, and get tasks methods here
    //TODO: Implement methods to assign tasks to users,
    //TODO: Implement methods to reassign tasks to another users,
    //TODO: Implement methods to task status (e.g., pending, in progress, completed, in_review)
    //TODO Find all tasks, find task by status, find task by user, find task by priority, find task by category.







    private Task findTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TestTodoException("Task not found with id: " + id));
    }
}
