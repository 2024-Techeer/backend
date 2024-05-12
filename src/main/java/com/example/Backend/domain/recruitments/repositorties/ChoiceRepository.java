package com.example.Backend.domain.recruitments.repositorties;

import com.example.Backend.domain.recruitments.entities.Answer;
import com.example.Backend.domain.recruitments.entities.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    List<Choice> findByAnswer(Answer answer);
}
