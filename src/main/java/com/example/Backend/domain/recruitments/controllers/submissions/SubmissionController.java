package com.example.Backend.domain.recruitments.controllers.submissions;

import com.example.Backend.domain.recruitments.dtos.submissions.SubmissionDetailDto;
import com.example.Backend.domain.recruitments.dtos.submissions.SubmissionReadDto;
import com.example.Backend.domain.recruitments.entities.submissions.Submission;
import com.example.Backend.domain.recruitments.repositorties.applications.ApplicationRepository;
import com.example.Backend.domain.recruitments.repositorties.recruitments.RecruitmentRepository;
import com.example.Backend.domain.recruitments.services.Email.EmailService;
import com.example.Backend.domain.recruitments.services.recruitments.RecruitmentService;
import com.example.Backend.domain.recruitments.services.submissions.SubmissionService;
import com.example.Backend.domain.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/applications/{applicationId}/submissions")
public class SubmissionController {
    @Autowired
    private SubmissionService submissionService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private RecruitmentRepository recruitmentRepository;
    @Autowired
    private UserRepository userRepository;

    // 제출서 생성
    @PostMapping
    public ResponseEntity<?> createSubmission(@PathVariable Long applicationId) {
        Submission submission = submissionService.createSubmission(applicationId);
        Recruitment recruitment = applicationRepository.findById(applicationId).get().getRecruitment();
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("submissionId", submission.getId()));
    }

    // 제출서 전체 조회
    @GetMapping
    public ResponseEntity<SubmissionReadDto> getSubmissions(@PathVariable Long applicationId) {
        SubmissionReadDto submissionDto = submissionService.getSubmissions(applicationId);
        if (submissionDto == null || submissionDto.getSubmissionIds().isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 Not Found 응답
        }
        return ResponseEntity.ok(submissionDto); // 200 OK 응답과 함께 DTO 반환
    }

    // 제출서 상세 조회
    @GetMapping("/{submissionId}")
    public ResponseEntity<SubmissionDetailDto> getSubmission(@PathVariable Long submissionId) {
        return submissionService.getSubmission(submissionId)
                .map(ResponseEntity::ok)  // 200 OK with data
                .orElseGet(() -> ResponseEntity.notFound().build());  // 404 Not Found if empty
    }

    // 제출서 수락
    @PatchMapping("/{submissionId}/accepting")
    public ResponseEntity<String> acceptSubmission(@PathVariable Long submissionId) {
        submissionService.acceptSubmission(submissionId);
        String responseMessage = "수락 완료: " + submissionId;
        return ResponseEntity.ok(responseMessage);
    }

    // 제출서 거절
    @PatchMapping("/{submissionId}/rejecting")
    public ResponseEntity<String> rejectSubmission(@PathVariable Long submissionId) {
        submissionService.rejectSubmission(submissionId);
        String responseMessage = "거절 완료: " + submissionId;
        return ResponseEntity.ok(responseMessage);
    }
}
