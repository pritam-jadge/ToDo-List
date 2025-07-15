package com.project.todolist.service;

import com.project.todolist.model.Task;
import com.project.todolist.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
