package com.example.Backend.domain.recruitments.controllers;

import com.example.Backend.domain.recruitments.dtos.ApplicationReadDto;
import com.example.Backend.domain.recruitments.entities.Application;
import com.example.Backend.domain.recruitments.services.ApplicationService;
import com.example.Backend.domain.recruitments.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/recruitments/{recruitmentId}/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    // 신청서 생성
    @PostMapping
    public ResponseEntity<?> createApplication(@PathVariable Long recruitmentId) {
        Application application = applicationService.createApplication(recruitmentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id", application.getId()));
    }

    // 신청서 조회
    @GetMapping
    public ResponseEntity<ApplicationReadDto> getApplication(@PathVariable Long recruitmentId) {
        return applicationService.getApplication(recruitmentId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
