package com.example.Backend.domain.recruitments.controllers.applications;

import com.example.Backend.domain.recruitments.dtos.applications.QuestionCreateDto;
import com.example.Backend.domain.recruitments.entities.applications.Question;
import com.example.Backend.domain.recruitments.services.applications.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/application/{applicationId}/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody QuestionCreateDto dto, @PathVariable Long applicationId) {
        try {
            Question question = questionService.createQuestion(dto, applicationId);
            return new ResponseEntity<>(question, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
