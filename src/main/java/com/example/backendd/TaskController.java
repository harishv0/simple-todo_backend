package com.example.backendd;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task")
@CrossOrigin(origins = "https://simplet-todo.netlify.app")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/addtask")
    public ResponseEntity<Task> addTask(@RequestParam("taskname") String taskname){
        System.out.println(taskname);
        Task task = new Task();
        task.setTaskName(taskname);
        task.setCompleted(false);
        taskService.save(task);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/gettasks")
    public ResponseEntity<List<Task>> getTasks(){
        return ResponseEntity.ok(taskService.allTask());
    }

    @DeleteMapping("/deletetask/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") long id) {
        try {
            taskService.delteByid(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/mark/{id}")
    public ResponseEntity<Task> mark(@PathVariable("id") long id) {
        Optional<Task> task = taskService.getById(id);
        if(task.get() != null){
            task.get().setCompleted(true);
            taskService.save(task.get());
        }
        return ResponseEntity.ok(task.get());
    }
}
