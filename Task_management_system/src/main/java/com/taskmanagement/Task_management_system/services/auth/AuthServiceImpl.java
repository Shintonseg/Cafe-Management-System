package com.taskmanagement.Task_management_system.services.auth;

import com.taskmanagement.Task_management_system.dto.SignupRequest;
import com.taskmanagement.Task_management_system.dto.UserDto;
import com.taskmanagement.Task_management_system.enums.UserRole;
import com.taskmanagement.Task_management_system.model.User;
import com.taskmanagement.Task_management_system.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    @PostConstruct
    public void createAdminAccount(){
       Optional<User> optionalUser = userRepository.findByUserRole(UserRole.ADMIN);
       if(optionalUser.isEmpty()){
           User user = new User();
           user.setName("admin");
           user.setEmail("admin@test.com");
           user.setUserRole(UserRole.ADMIN);
           user.setPassword(new BCryptPasswordEncoder().encode("password"));
           userRepository.save(user);
           System.out.println("Admin account created");
       }else {
           System.out.println("Admin account already exists");
       }
    }

    @Override
    public UserDto signupUser(SignupRequest signupRequest) {
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setUserRole(UserRole.EMPLOYEE);
        User createdUser = userRepository.save(user);
        return createdUser.getUserDto();
    }

    @Override
    public boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}
