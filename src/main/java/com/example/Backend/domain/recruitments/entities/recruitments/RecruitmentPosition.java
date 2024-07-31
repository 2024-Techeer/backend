package com.example.Backend.domain.recruitments.entities.recruitments;

import com.example.Backend.domain.common.entities.Position;
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
@Table(name = "recruitment_positioins")
public class RecruitmentPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recruitment_id", nullable = false)
    private Recruitment recruitment;

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;
}
//Recruitment --- Position 중간테이블
/*
@ManyToOne 관계와 @JoinColumn 애너테이션을 사용하면, 해당 필드에 외래 키(Foreign Key) 컬럼이 생성됩니다
*/
