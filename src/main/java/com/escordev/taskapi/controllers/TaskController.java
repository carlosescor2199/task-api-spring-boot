package com.escordev.taskapi.controllers;

import com.escordev.taskapi.dto.TaskDTO;
import com.escordev.taskapi.persistence.models.Task;
import com.escordev.taskapi.persistence.models.TaskStatus;
import com.escordev.taskapi.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO task) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.taskService.createTask(task));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.taskService.getAllTasks());
    }

    @GetMapping("/{status}")
    public ResponseEntity<List<Task>> getTasksByStatus(@PathVariable TaskStatus status) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.taskService.getAllTaskByTaskStatus(status));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable Long id) {
        this.taskService.updateTaskAsCompleted(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        this.taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
