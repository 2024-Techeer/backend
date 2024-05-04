package com.example.Backend.domain.recruitments.controllers;

import com.example.Backend.domain.recruitments.dtos.RecruitmentCreateDto;
import com.example.Backend.domain.recruitments.entities.Recruitment;
import com.example.Backend.domain.recruitments.services.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/recruitments")
public class RecruitmentsController {

    @Autowired
    private RecruitmentService recruitmentService;

    @PostMapping
    public ResponseEntity<?> createRecruitment(@RequestBody RecruitmentCreateDto dto) {
        try {
            Recruitment recruitment = recruitmentService.createRecruitment(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id", recruitment.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "생성 실패"));
        }
    }
}
