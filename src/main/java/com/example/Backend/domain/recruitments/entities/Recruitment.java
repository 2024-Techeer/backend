package com.example.Backend.domain.recruitments.entities;

import com.example.Backend.domain.common.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.Backend.domain.user.User;

import java.util.Date;

@Entity
@Table(name = "recruitments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recruitment extends BaseEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ManyToOne
   @JoinColumn(name = "user_id", nullable = false)
   private User user;

   @Column(nullable = false)
   private String type;

   @Column(nullable = false)
   private String title;

   @Column(nullable = false)
   private int number;

   // TemporalType.DATE로 설정함으로써 날짜만 저장할 것이라는 것을 명시(자바의 Date 타입은 시간까지 포함됨)
   @Temporal(TemporalType.DATE)
   @Column(nullable = false)
   private Date startDate;

   @Temporal(TemporalType.DATE)
   @Column(nullable = false)
   private Date endDate;

   @Temporal(TemporalType.DATE)
   @Column(nullable = false)
   private Date deadline;

   @Column(nullable = false)
   private boolean closing = false;
}
