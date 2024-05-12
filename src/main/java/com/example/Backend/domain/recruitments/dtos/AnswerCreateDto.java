package com.example.Backend.domain.recruitments.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnswerCreateDto {
    private String content;
    private List<Long> optionIds;
}
