package com.example.Backend.domain.recruitments.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionReadDto {
    private Long questionId;
    private String type;
    private String title;
    private List<OptionReadDto> options;
}
