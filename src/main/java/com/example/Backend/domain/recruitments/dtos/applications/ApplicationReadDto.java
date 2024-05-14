package com.example.Backend.domain.recruitments.dtos.applications;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApplicationReadDto {
    private Long applicatoinId;
    private List<QuestionReadDto> questions;

}
