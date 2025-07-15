package com.project.todolist.controller;

import com.project.todolist.model.Task;
import com.project.todolist.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/getAllTasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        try {
            List<Task> tasks = taskService.getAllTasks();
            return ResponseEntity.status(HttpStatus.OK).body(tasks);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/createTask")
    public ResponseEntity<String> createTask(@RequestBody Task task) {
        try {
            taskService.createTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body("Task created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create task");

        }
    }

    @GetMapping("/getActiveTasks")
    public ResponseEntity<List<Task>> getAllActiveTasks() {
        try {
            List<Task> tasks = taskService.getAllActiveTasks();
            return ResponseEntity.status(HttpStatus.OK).body(tasks);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable("id") Long taskId) {
        try {
            boolean flag = taskService.deleteTaskById(taskId);
            if (flag) return ResponseEntity.status(HttpStatus.OK).body("Task Deleted Successfully");
            else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found with id: " + taskId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @PutMapping("/updateTask")
    public ResponseEntity<String> updateTask(@RequestBody Task task) {
        return taskService.updateTaskDetails(task)
                .map(updated -> ResponseEntity.ok("Updated"))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task Not Found"));
    }

    @GetMapping("test")
    public void test() {
        System.out.println("Test");
    }
}
