package com.example.Backend.domain.recruitments.services;

import com.example.Backend.domain.recruitments.entities.Application;
import com.example.Backend.domain.recruitments.entities.Recruitment;
import com.example.Backend.domain.recruitments.repositorties.ApplicationRepository;
import com.example.Backend.domain.recruitments.repositorties.RecruitmentRepository;
import com.example.Backend.domain.user.User;
import com.example.Backend.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private RecruitmentRepository recruitmentRepository;
    @Autowired
    private UserRepository userRepository;

    public Application createApplication(Long recruitmentId) {
        Application application = new Application();

        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new RuntimeException("해당 모집글은 존재하지 않습니다: " + recruitmentId));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // 유저의 이메일 추출
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userEmail)); // 유저의 이메일을 기준으로 User 인스턴스 추출

        if(!recruitment.getUser().equals(user)) {
            throw new IllegalStateException("신청서 생성 권한이 없는 모집글입니다.");
        }

        application.setRecruitment(recruitment);
        application = applicationRepository.save(application);
        return application;
    }

}
