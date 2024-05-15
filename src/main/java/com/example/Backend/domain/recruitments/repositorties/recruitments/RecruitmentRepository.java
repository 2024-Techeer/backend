package com.example.Backend.domain.recruitments.repositorties.recruitments;

import com.example.Backend.domain.recruitments.entities.recruitments.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;



public interface RecruitmentRepository extends JpaRepository<Recruitment, Long>, JpaSpecificationExecutor<Recruitment>{


}
