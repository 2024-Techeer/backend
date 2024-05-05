package com.example.Backend.domain.recruitments.repositorties;

import com.example.Backend.domain.recruitments.entities.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
}
