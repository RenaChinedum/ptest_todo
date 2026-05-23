package com.rena.testtodo.service;

import com.rena.testtodo.dto.request.CreateTaskRequest;
import com.rena.testtodo.dto.request.UpdateTaskRequest;
import com.rena.testtodo.dto.response.GenericResponse;
import com.rena.testtodo.dto.response.TaskResponse;
import com.rena.testtodo.enums.Category;
import com.rena.testtodo.enums.Priority;
import com.rena.testtodo.enums.Status;
import com.rena.testtodo.enums.StatusCode;
import com.rena.testtodo.exception.TestTodoException;
import com.rena.testtodo.persitance.entity.Task;
import com.rena.testtodo.persitance.entity.Users;
import com.rena.testtodo.persitance.repository.TaskRepository;
import com.rena.testtodo.persitance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.rena.testtodo.dto.request.CreateTaskRequest.fromRequest;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    //TODO: Implement create, update, delete, and get tasks methods here
    //TODO: Implement methods to assign tasks to users,
    //TODO: Implement methods to reassign tasks to another users,
    //TODO: Implement methods to task status (e.g., pending, in progress, completed, in_review)
    //TODO Find all tasks, find task by status, find task by user, find task by priority, find task by category.
    //TODO: get user task by priority, get user task by category
    // TODO: change task status, priority, category

public GenericResponse<TaskResponse> createTask(CreateTaskRequest request){
Task task = new Task();
task.setTitle(request.getTitle());
task.setDescription(request.getDescription());
task.setCategory(request.getCategory());
task.setPriority(request.getPriority());
task.setStatus(Status.valueOf(Status.PENDING.name()));
task.setCreatedAt(LocalDateTime.now());
task.setUpdatedAt(LocalDateTime.now());
taskRepository.save(task);
return new GenericResponse<>("Task Created Successfully",TaskResponse.fromEntity(task),200);
}

public GenericResponse<TaskResponse> createTaskByBuilder(CreateTaskRequest request){
    return new GenericResponse<>("Task Created Successfully",TaskResponse
            .fromEntity(taskRepository.save(fromRequest(request))),200);
}

public GenericResponse<TaskResponse> updateTask(Long id, UpdateTaskRequest request){
    Task task = findTaskById(id);
    task.setTitle(request.getTitle());
    task.setDescription(request.getDescription());
    task.setCategory(request.getCategory());
    task.setPriority(request.getPriority());
    task.setUpdatedAt(LocalDateTime.now());
    taskRepository.save(task);
    return new GenericResponse<>("Task Updated Successfully",TaskResponse.fromEntity(task),StatusCode.SUCCESS_CODE.getCode());
}

public GenericResponse<?> deleteTask(Long id){
    Task task = findTaskById(id);
    taskRepository.delete(task);
    return new GenericResponse<>("Task Deleted Successfully",null,StatusCode.SUCCESS_CODE.getCode());
}

public GenericResponse<TaskResponse> getTaskById(Long id){
    Task task =findTaskById(id);
   return new GenericResponse<>("Task Retrieved Successfully",TaskResponse.fromEntity(task), StatusCode.SUCCESS_CODE.getCode());

}

public GenericResponse<TaskResponse> assignTaskToUser(Long id, Long userId){
    Task task = findTaskById(id);
    Users user = userRepository.findById(userId).orElseThrow(() -> new TestTodoException("User not found with Id: " + userId));
    task.setUser(user);
    task.setUpdatedAt(LocalDateTime.now());
    taskRepository.save(task);
    return new GenericResponse<>("Task Assigned Successfully", TaskResponse.fromEntity(task), StatusCode.SUCCESS_CODE.getCode());

}

public GenericResponse <List<TaskResponse>> getAllTask(){
    List<Task> tasks = taskRepository.findAll();
    List<TaskResponse> response = tasks.stream()
            .map(TaskResponse::fromEntity)
            .toList();
    return new GenericResponse<>("Tasks retrieved Successfully", response, StatusCode.SUCCESS_CODE.getCode());
}

public GenericResponse<List<TaskResponse>> getTasksByStatus(String status){
List<Task> tasks = taskRepository.findByStatus(status);
List<TaskResponse> responses = tasks.stream().map(TaskResponse::fromEntity).collect(Collectors.toList());
return new GenericResponse<>("Task Retrieved Successfully", responses, StatusCode.SUCCESS_CODE.getCode());
}

public GenericResponse<List<TaskResponse>> getTaskByUser(Long userId){
    List<Task> tasks = taskRepository.findByUserId(userId);
    List<TaskResponse> responses = tasks.stream().map(TaskResponse::fromEntity).collect(Collectors.toList());
    return new GenericResponse<>("Task Retrieved Successfully",responses,StatusCode.SUCCESS_CODE.getCode());
}


    public GenericResponse<List<TaskResponse>> getUserTasksByCategory(Long userId, Category category) {
        List<Task> tasks = taskRepository.findAll().stream().filter(t -> t.getCategory() == category).toList();
        List<TaskResponse> response = tasks.stream().map(TaskResponse::fromEntity).toList();
        return new GenericResponse<>("Tasks Retrieved Successfully", response, StatusCode.SUCCESS_CODE.getCode());
    }

    public GenericResponse<List<TaskResponse>> getUserTaskByPriority(Long userId, Priority priority){
List<Task> tasks = taskRepository.findByUserId(userId).stream().filter(task -> task.getPriority() == priority).toList();
List<TaskResponse> responses = tasks.stream().map(TaskResponse::fromEntity).toList();
        return new GenericResponse<>("Tasks Retrieved Successfully", responses, StatusCode.SUCCESS_CODE.getCode());
    }


    public GenericResponse<TaskResponse> updateTaskStatus(Long id, Status status){
    Task task = findTaskById(id);
    task.setStatus(status);
    task.setUpdatedAt(LocalDateTime.now());
    taskRepository.save(task);
    return new GenericResponse<>("Status Updated Successfully", TaskResponse.fromEntity(task),StatusCode.SUCCESS_CODE.getCode());
    }

    public GenericResponse<TaskResponse> updateTaskPriority(Long id, Priority priority){
    Task task = findTaskById(id);
    task.setPriority(priority);
    task.setUpdatedAt(LocalDateTime.now());
    taskRepository.save(task);
    return new GenericResponse<>("Priority Updated Successfully", TaskResponse.fromEntity(task), StatusCode.SUCCESS_CODE.getCode());
    }

    public GenericResponse<TaskResponse> updateTaskCategory(Long id, Category category){
    Task task = findTaskById(id);
    task.setCategory(category);
    task.setUpdatedAt(LocalDateTime.now());
    taskRepository.save(task);
    return new GenericResponse<>("Category Updated Successfully", TaskResponse.fromEntity(task),StatusCode.SUCCESS_CODE.getCode());
    }

    private Task findTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TestTodoException("Task not found with id: " + id));
    }
}
