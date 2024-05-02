package com.timetrackerBackend.timetrackerBackend.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.timetrackerBackend.timetrackerBackend.models.Task;
import com.timetrackerBackend.timetrackerBackend.services.TaskService;


@RestController
public class TaskController {
    private TaskService taskService;

    public TaskController (TaskService taskService){
         this.taskService=taskService;
    }

    @PostMapping("/task")
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }   

    @PostMapping("/task/start/{taskId}")
    public Task startTask(@PathVariable String taskId) {
        return taskService.startTask(taskId);
    }

    @PostMapping("/task/stop/{taskId}")
    public Task stopTask(@PathVariable String taskId) {
        return taskService.stopTask(taskId);
    }
}

