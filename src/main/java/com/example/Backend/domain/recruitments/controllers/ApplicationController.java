package com.example.Backend.domain.recruitments.controllers;

import com.example.Backend.domain.recruitments.entities.Application;
import com.example.Backend.domain.recruitments.services.ApplicationService;
import com.example.Backend.domain.recruitments.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    // 신청서 생성
    @PostMapping("/{recruitmentId}")
    public ResponseEntity<?> createApplication(@PathVariable Long recruitmentId) {
        Application application = applicationService.createApplication(recruitmentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id", application.getId()));
    }

}
