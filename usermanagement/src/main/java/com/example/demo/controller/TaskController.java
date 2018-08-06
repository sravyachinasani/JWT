//package com.example.demo.controller;
//
//import com.example.demo.Repository.TaskRepository;
//import com.example.demo.entity.Tasks;
//import org.springframework.util.Assert;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/tasks")
//public class TaskController {
//    private TaskRepository taskRepository;

//    public TaskController(TaskRepository taskRepository) {
//        this.taskRepository = taskRepository;
//    }
//
//    @PostMapping
//    public void addTask(@RequestBody Tasks task) {
//        taskRepository.save(task);
//    }
//
//    @GetMapping
//    public List<Tasks> getTasks() {
//        return taskRepository.findAll();
//    }

//    @PutMapping("/{id}")
//    public void editTask(@PathVariable long id, @RequestBody Tasks task) {
//        Tasks existingTask = taskRepository.findOne(id);
//        Assert.notNull(existingTask, "Task not found");
//        existingTask.setDescription(task.getDescription());
//        taskRepository.save(existingTask);
//    }

//    @DeleteMapping("/{id}")
//    public void deleteTask(@PathVariable long id) {
//        taskRepository.delete(id);
//    }
//}
//}
