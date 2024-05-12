package com.example.Backend.domain.recruitments.repositorties;

import com.example.Backend.domain.recruitments.entities.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByApplicationId(Long applicationId);
}
