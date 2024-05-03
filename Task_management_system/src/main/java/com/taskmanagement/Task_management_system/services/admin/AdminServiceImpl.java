package com.taskmanagement.Task_management_system.services.admin;

import com.taskmanagement.Task_management_system.dto.UserDto;
import com.taskmanagement.Task_management_system.enums.UserRole;
import com.taskmanagement.Task_management_system.model.User;
import com.taskmanagement.Task_management_system.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final UserRepository userRepository;

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getUserRole()== UserRole.EMPLOYEE)
                .map(User::getUserDto)
                .collect(Collectors.toList());
    }
}
