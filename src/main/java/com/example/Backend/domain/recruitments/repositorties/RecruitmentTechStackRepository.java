package com.example.Backend.domain.recruitments.repositorties;

import com.example.Backend.domain.recruitments.entities.RecruitmentTechStack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentTechStackRepository extends JpaRepository<RecruitmentTechStack, Long> {
}
