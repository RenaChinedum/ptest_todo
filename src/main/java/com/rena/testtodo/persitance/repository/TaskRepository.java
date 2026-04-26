package com.rena.testtodo.persitance.repository;

import com.rena.testtodo.persitance.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findById(long id);
    List<Task> findByUserId(Long id);
    List<Task> findByStatus(String status);

    @Query("SELECT t FROM Task t WHERE t.user.id = :userId AND t.status = :status")
    List<Task> findByUserIdAndStatus(Long userId, String status);

    @Query("UPDATE Task t SET t.status = :status WHERE t.id = :id")
    Task updateStatusById(@Param("id") Long id, @Param("status") String status);
}
