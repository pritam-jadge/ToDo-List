package com.project.todolist.service;

import com.project.todolist.model.Task;
import com.project.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = this.taskRepository.findAll();
        tasks.forEach(System.out::println);
        return tasks;
    }

    public void createTask(Task task) {
        taskRepository.save(task);
    }

    public List<Task> getAllActiveTasks() {
        return taskRepository.findByStatusTrue();
    }

    public boolean deleteTaskById(Long taskId) {

        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
            return true;
        } else {
            return false;
        }
    }

    public Optional<Task> updateTaskDetails(Task task) {
        return taskRepository.findById(task.getId())
                .map(existing -> {
                    existing.setTitle(task.getTitle());
                    existing.setDescription(task.getDescription());
                    existing.setDueDate(task.getDueDate());
                    existing.setStatus(task.isStatus());
                    existing.setCreatedAt(LocalDateTime.now());
                    return taskRepository.save(existing);
                });
    }
}
