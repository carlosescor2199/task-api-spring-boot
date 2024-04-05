package com.escordev.taskapi.services;

import com.escordev.taskapi.dto.TaskDTO;
import com.escordev.taskapi.exceptions.ToDoExceptions;
import com.escordev.taskapi.mapper.TaskDTOToTask;
import com.escordev.taskapi.persistence.models.Task;
import com.escordev.taskapi.persistence.models.TaskStatus;
import com.escordev.taskapi.persistence.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskDTOToTask mapper;

    public TaskService(TaskRepository taskRepository, TaskDTOToTask mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    public Task createTask(TaskDTO taskDTO) {
        Task task = this.mapper.map(taskDTO);
        return this.taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return this.taskRepository.findAll();
    }

    public List<Task> getAllTaskByTaskStatus(TaskStatus taskStatus) {
        return this.taskRepository.findAllByTaskStatus(taskStatus);
    }

    @Transactional
    public void updateTaskAsCompleted(Long id) {
        Optional<Task> optionalTask = this.taskRepository.findById(id);

        if (optionalTask.isEmpty()) {
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }

        this.taskRepository.markTaskAsCompleted(id);
    }

    public void deleteTask(Long id) {
        Optional<Task> optionalTask = this.taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }

        this.taskRepository.deleteById(id);
    }
}
