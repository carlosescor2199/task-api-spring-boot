package com.escordev.taskapi.persistence.repository;

import com.escordev.taskapi.persistence.models.Task;
import com.escordev.taskapi.persistence.models.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByTaskStatus(TaskStatus taskStatus);

    @Modifying
    @Query(value = "UPDATE tasks SET completed=true WHERE id=:id", nativeQuery = true)
    void markTaskAsCompleted(@Param("id") Long id);
}
