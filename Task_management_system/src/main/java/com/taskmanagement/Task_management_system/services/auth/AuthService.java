package com.taskmanagement.Task_management_system.services.auth;

import com.taskmanagement.Task_management_system.dto.SignupRequest;
import com.taskmanagement.Task_management_system.dto.UserDto;

public interface AuthService {

    UserDto signupUser(SignupRequest signupRequest);
    boolean hasUserWithEmail(String email);
}
