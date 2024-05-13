package com.example.Backend.domain.recruitments.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubmissionDetailDto {
    private Long userId;
    private String status;
    private List<AnswerReadDto> answers;
}
