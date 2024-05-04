package com.example.Backend.domain.recruitments.repositorties;

import com.example.Backend.domain.recruitments.entities.RecruitmentPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentPositionRepository extends JpaRepository<RecruitmentPosition, Long> {
}
