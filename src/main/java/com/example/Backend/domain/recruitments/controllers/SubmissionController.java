package com.example.Backend.domain.recruitments.controllers;

import com.example.Backend.domain.recruitments.dtos.SubmissionDetailDto;
import com.example.Backend.domain.recruitments.dtos.SubmissionReadDto;
import com.example.Backend.domain.recruitments.entities.Submission;
import com.example.Backend.domain.recruitments.services.SubmissionService;
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

    // 제출서 생성
    @PostMapping
    public ResponseEntity<?> createSubmission(@PathVariable Long applicationId) {
        Submission submission = submissionService.createSubmission(applicationId);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id", submission.getId()));
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

    @GetMapping("/{submissionId}")
    public ResponseEntity<SubmissionDetailDto> getSubmission(@PathVariable Long submissionId) {
        return submissionService.getSubmission(submissionId)
                .map(submissionDetailDto -> ResponseEntity.ok(submissionDetailDto))  // 200 OK with data
                .orElseGet(() -> ResponseEntity.notFound().build());  // 404 Not Found if empty
    }
}
