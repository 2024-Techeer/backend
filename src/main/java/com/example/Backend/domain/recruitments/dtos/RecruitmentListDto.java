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
    private Integer number;
    private Date startDate;
    private Date endDate;
    private Date deadline;
    private Boolean closing;
    private List<String> positions;
    private List<String> techStacks;
    private Long userId;
}
