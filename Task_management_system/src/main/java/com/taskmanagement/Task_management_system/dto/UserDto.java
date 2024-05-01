package com.taskmanagement.Task_management_system.dto;

import com.taskmanagement.Task_management_system.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private UserRole userRole;
}
