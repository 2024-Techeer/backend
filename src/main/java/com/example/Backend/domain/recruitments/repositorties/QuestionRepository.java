package com.example.Backend.domain.recruitments.repositorties;

import com.example.Backend.domain.recruitments.entities.Application;
import com.example.Backend.domain.recruitments.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByApplication(Application application);
}
