package com.example.Backend.domain.recruitments.entities.recruitments;

import com.example.Backend.domain.common.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.Backend.domain.user.entities.User;

import java.util.Date;
import java.util.List;

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

   //스터디 or 프로젝트
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

   @Column(nullable = true)
   private String introduction;

   @OneToMany(mappedBy = "recruitment", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<RecruitmentPosition> positions;

   @OneToMany(mappedBy = "recruitment", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<RecruitmentTechStack> techStacks;

   /*
   @OneToMany 관계를 사용한 필드(positions와 techStacks)는 직접적으로 데이터베이스 테이블의 컬럼으로 생성되지 않습니다. 
   대신, 이러한 관계는 데이터베이스에서 연관된 테이블과의 관계를 정의하며, 실제 컬럼은 연관된 엔터티 클래스의 테이블에서 생성
   */

   /*
   private List<RecruitmentTechStack> techStacks를 리스트로 선언한 이유는 하나의 Recruitment 엔티티가 여러 개의 RecruitmentTechStack 엔티티와 연관될 수 있기 때문입니다. 
   이는 일대다(One-to-Many) 관계를 나타내며, 한 개의 모집 공고(Recruitment)가 여러 기술 스택(RecruitmentTechStack)을 가질 수 있다는 것을 의미합니다.

예를 들어, 특정 프로젝트에 필요한 여러 기술(예: Java, Python, React 등)을 표현하고 싶다면, RecruitmentTechStack 엔티티를 사용하여 각각의 기술을 별도로 저장하고 관리할 수 있습니다.
따라서, 리스트를 사용하여 Recruitment 엔티티와 연관된 모든 기술 스택을 한 번에 가져오거나 관리할 수 있습니다.
   */
}
