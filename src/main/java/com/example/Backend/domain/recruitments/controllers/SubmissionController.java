package com.example.Backend.domain.recruitments.controllers;

import com.example.Backend.domain.recruitments.entities.Submission;
import com.example.Backend.domain.recruitments.services.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
