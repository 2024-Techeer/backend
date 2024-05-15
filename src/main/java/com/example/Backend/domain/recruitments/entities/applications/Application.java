package com.example.Backend.domain.recruitments.entities.applications;

import com.example.Backend.domain.common.entities.BaseEntity;
import com.example.Backend.domain.recruitments.entities.recruitments.Recruitment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "applications")
public class Application extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "recruitment_id", nullable = false)
    private Recruitment recruitment;
}
