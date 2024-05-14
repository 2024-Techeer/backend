package com.example.Backend.domain.recruitments.repositorties.submissions;

import com.example.Backend.domain.recruitments.entities.submissions.Answer;
import com.example.Backend.domain.recruitments.entities.submissions.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    List<Choice> findByAnswer(Answer answer);
}
