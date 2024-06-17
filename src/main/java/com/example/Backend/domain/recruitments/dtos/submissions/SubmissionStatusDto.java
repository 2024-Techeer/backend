package com.example.Backend.domain.recruitments.dtos.submissions;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class SubmissionStatusDto {
    private Long id;
    private String type;
    private String title;
    private Integer number;
    private Date startDate;
    private Date endDate;
    private Date deadline;

    @Getter
    private String status;

    private List<Long> positionIds;
    private List<Long> techStackIds;

}
