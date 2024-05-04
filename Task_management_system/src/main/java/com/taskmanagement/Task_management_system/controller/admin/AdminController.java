package com.taskmanagement.Task_management_system.controller.admin;

import com.taskmanagement.Task_management_system.dto.TaskDTO;
import com.taskmanagement.Task_management_system.services.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok(adminService.getUsers());
    }

    @PostMapping("/task")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO){
        TaskDTO createdTaskDTO = adminService.createTask(taskDTO);
        if(createdTaskDTO == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTaskDTO);
    }
}
