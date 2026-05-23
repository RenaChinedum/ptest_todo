package com.rena.testtodo.controller;


import com.rena.testtodo.dto.request.CreateTaskRequest;
import com.rena.testtodo.dto.request.UpdateTaskRequest;
import com.rena.testtodo.dto.response.GenericResponse;
import com.rena.testtodo.dto.response.TaskResponse;
import com.rena.testtodo.enums.Category;
import com.rena.testtodo.enums.Priority;
import com.rena.testtodo.enums.Status;
import com.rena.testtodo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor


public class TaskController {
    private final TaskService taskService;

    @PostMapping("/create")
    public GenericResponse<TaskResponse> createTask(@RequestBody CreateTaskRequest request){
        return taskService.createTask(request);
    }
    @PutMapping("/update/{id}")
    public GenericResponse<TaskResponse> updateTask(@PathVariable Long id,@RequestBody UpdateTaskRequest request){
        return taskService.updateTask(id,request);
    }
    @DeleteMapping("/delete/{id}")
    public GenericResponse<?> deleteTask(@PathVariable Long id){
        return taskService.deleteTask(id);
    }
    @GetMapping("/get/{id}")
    public GenericResponse<TaskResponse> getTaskById (@PathVariable Long id){
        return taskService.getTaskById(id);
    }
    @PutMapping("/assign/{taskId}/user/{userId}")
public GenericResponse<TaskResponse> assignTask (@PathVariable Long taskId, @PathVariable Long userId){
        return taskService.assignTaskToUser(taskId,userId);
    }

    @GetMapping("/all")
    public GenericResponse<List<TaskResponse>> getAllTasks() {
        return taskService.getAllTask();
    }

    @GetMapping("/by-status")
    public GenericResponse<List<TaskResponse>> getByStatus(@RequestParam String status) {
        return taskService.getTasksByStatus(status);
    }

    @GetMapping("/by-user/{userId}")
    public GenericResponse<List<TaskResponse>> getByUser(@PathVariable Long userId) {
        return taskService.getTaskByUser(userId);
    }

    @GetMapping("/by-user/{userId}/priority")
    public GenericResponse<List<TaskResponse>> getUserTaskByPriority(
            @PathVariable Long userId,
            @RequestParam Priority priority) {
        return taskService.getUserTaskByPriority(userId, priority);
    }

        @GetMapping("/by-user/{userId}/category")
        public GenericResponse<List<TaskResponse>> getTaskByUserAndCategory(
                @PathVariable Long userId,
                @RequestParam Category category) {
            return taskService.getUserTasksByCategory(userId, category);
        }

        @PatchMapping("/change-status/{id}")
    public GenericResponse<TaskResponse> updateTaskStatus(
        @PathVariable Long id,
        @RequestParam Status status){
        return taskService.updateTaskStatus(id, status);
        }
        @PatchMapping("/update-category/{id}")
    public GenericResponse<TaskResponse> updateTaskCategory(
            @PathVariable Long id,
            @RequestParam Category category){
        return taskService.updateTaskCategory(id, category);
        }
        @PatchMapping("/update-priority/{id}")
    public GenericResponse<TaskResponse> updateTaskPriority(
            @PathVariable Long id,
            @RequestParam Priority priority){
        return taskService.updateTaskPriority(id, priority);
        }

    }


