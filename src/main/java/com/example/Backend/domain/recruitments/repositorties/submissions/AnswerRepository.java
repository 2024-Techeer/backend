package com.example.Backend.domain.recruitments.repositorties.submissions;

import com.example.Backend.domain.recruitments.entities.submissions.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findBySubmissionId(Long sumissionId);
}
