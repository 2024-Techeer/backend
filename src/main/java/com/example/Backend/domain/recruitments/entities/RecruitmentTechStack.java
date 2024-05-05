package com.example.Backend.domain.recruitments.entities;

import com.example.Backend.domain.common.entities.TechStack;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recruitment_techstacks")
public class RecruitmentTechStack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recruitment_id", nullable = false)
    private Recruitment recruitment;

    @ManyToOne
    @JoinColumn(name = "techStack_id", nullable = false)
    private TechStack techStack;
}
