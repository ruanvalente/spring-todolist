package com.todolist.todolist.controller;

import com.todolist.todolist.model.Task;
import com.todolist.todolist.services.TaskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class TaskController {

    @Autowired
    TaskServices taskServices;

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        return taskServices.createTask(task);
    }

    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> listAllTasks() {
        return taskServices.listAllTasks();
    }

    @GetMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> getTasksById(@PathVariable(value = "id") Long id) {
        Optional<Task> task = taskServices.getTaskById(id);

        if (task.isPresent()) {
            return new ResponseEntity<Task>(task.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
