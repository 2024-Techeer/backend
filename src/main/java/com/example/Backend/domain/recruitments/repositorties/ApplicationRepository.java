package com.example.Backend.domain.recruitments.repositorties;

import com.example.Backend.domain.recruitments.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    // Recruitment의 ID를 사용하여 관련 Application을 찾는 메소드
    Optional<Application> findByRecruitmentId(Long recruitmentId);
}
