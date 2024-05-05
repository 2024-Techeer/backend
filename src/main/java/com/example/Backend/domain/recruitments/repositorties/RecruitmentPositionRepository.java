package com.example.Backend.domain.recruitments.repositorties;

import com.example.Backend.domain.recruitments.entities.Recruitment;
import com.example.Backend.domain.recruitments.entities.RecruitmentPosition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitmentPositionRepository extends JpaRepository<RecruitmentPosition, Long> {
    List<RecruitmentPosition> findByRecruitment(Recruitment recruitment);

}
