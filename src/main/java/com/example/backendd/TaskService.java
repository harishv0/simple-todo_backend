package com.example.backendd;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepo;

    public void save(Task task) {
        taskRepo.save(task);
    }

    public List<Task> allTask(){
        return taskRepo.findAll();
    }

    public void delteByid(long id) {
        if (taskRepo.existsById(id)) {
            taskRepo.deleteById(id);
        } else {
            throw new RuntimeException("Task not found with id: " + id);
        }
    }

    public Optional<Task> getById(long id){
        return taskRepo.findById(id);
    }
}
