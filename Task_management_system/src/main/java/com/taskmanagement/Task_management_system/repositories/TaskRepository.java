package com.taskmanagement.Task_management_system.repositories;

import com.taskmanagement.Task_management_system.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
}
