package com.example.Backend.domain.recruitments.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ApplicationReadDto {
    private Long applicatoinId;
    private List<QuestionReadDto> questions;

}
