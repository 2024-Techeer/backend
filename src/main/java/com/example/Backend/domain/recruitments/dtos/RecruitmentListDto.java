package com.example.Backend.domain.recruitments.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class RecruitmentListDto {
    private Long id;
    private String title;
    private String type;
    private int number;
    private Date startDate;
    private Date endDate;
    private Date deadline;
    private boolean closing;
    private List<String> positions;
    private List<String> techStacks;
    private Long userId;
}
