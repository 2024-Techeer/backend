package com.example.Backend.domain.user.repositories;

import com.example.Backend.domain.user.entities.User;
import com.example.Backend.domain.user.entities.UserTechStack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTechStackRepository extends JpaRepository<UserTechStack,Long> {
    List<UserTechStack> findByUser(User user);
}
