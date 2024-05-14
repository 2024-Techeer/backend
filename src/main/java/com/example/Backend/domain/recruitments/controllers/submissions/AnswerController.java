package com.example.Backend.domain.recruitments.controllers.submissions;

import com.example.Backend.domain.recruitments.dtos.submissions.AnswerCreateDto;
import com.example.Backend.domain.recruitments.entities.submissions.Answer;
import com.example.Backend.domain.recruitments.services.submissions.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/applications/questions/{questionId}/submissions/{submissionId}/answers")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @PostMapping
    public ResponseEntity<Answer> createAnswer(@RequestBody AnswerCreateDto dto,
                                               @PathVariable Long questionId,
                                               @PathVariable Long submissionId) {
        try {
            Answer answer = answerService.createAnswer(dto, questionId, submissionId);
            return new ResponseEntity<>(answer, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
