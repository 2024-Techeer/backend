package com.example.Backend.domain.recruitments.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class RecruitmentDetailDto {
    private Long id;
    private String title;
    private String type;
    private Integer number;
    private Date startDate;
    private Date endDate;
    private Date deadline;
    private Boolean closing;
    private List<String> positions; // Position의 이름을 담는 리스트
    private List<String> techStacks; // TechStack의 이름을 담는 리스트
    private Long userId; // User의 ID
}

