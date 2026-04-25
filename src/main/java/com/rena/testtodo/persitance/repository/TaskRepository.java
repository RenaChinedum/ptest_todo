package com.rena.testtodo.persitance.repository;

import com.rena.testtodo.persitance.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findById(long id);

    @Query("SELECT t FROM Task t WHERE t.status = 'COMPLETED'")
    List<Task> completedTasks();

    @Query("SELECT t FROM Task t WHERE t.status = 'DONE'")
    List<Task> Doneasks();
}
