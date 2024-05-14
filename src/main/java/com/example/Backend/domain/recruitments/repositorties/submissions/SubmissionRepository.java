package com.example.Backend.domain.recruitments.repositorties.submissions;

import com.example.Backend.domain.recruitments.entities.submissions.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByApplicationId(Long applicationId);
}
