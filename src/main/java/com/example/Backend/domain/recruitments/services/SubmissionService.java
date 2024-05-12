package com.example.Backend.domain.recruitments.services;

import com.example.Backend.domain.recruitments.dtos.SubmissionReadDto;
import com.example.Backend.domain.recruitments.entities.Application;
import com.example.Backend.domain.recruitments.entities.Submission;
import com.example.Backend.domain.recruitments.repositorties.AnswerRepository;
import com.example.Backend.domain.recruitments.repositorties.ApplicationRepository;
import com.example.Backend.domain.recruitments.repositorties.SubmissionRepository;
import com.example.Backend.domain.user.User;
import com.example.Backend.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionService {
    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnswerRepository answerRepository;

    public Submission createSubmission(Long applicationId) {
        Submission submission = new Submission();

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("해당 신청서 양식은 존재하지 않습니다: " + applicationId));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // 유저의 이메일 추출
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userEmail)); // 유저의 이메일을 기준으로 User 인스턴스 추출

        submission.setApplication(application);
        submission.setUser(user);

        return submissionRepository.save(submission);

    }


}
