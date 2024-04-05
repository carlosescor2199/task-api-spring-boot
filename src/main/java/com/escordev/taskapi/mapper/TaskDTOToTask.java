package com.escordev.taskapi.mapper;

import com.escordev.taskapi.dto.TaskDTO;
import com.escordev.taskapi.persistence.models.Task;
import com.escordev.taskapi.persistence.models.TaskStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskDTOToTask implements IMapper<TaskDTO, Task> {
    @Override
    public Task map(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setEta(taskDTO.getEta());
        task.setCreatedAt(LocalDateTime.now());
        task.setTaskStatus(TaskStatus.ON_TIME);
        task.setCompleted(false);
        return task;
    }
}
