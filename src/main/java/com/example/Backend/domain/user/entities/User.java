package com.example.Backend.domain.user.entities;

import com.example.Backend.domain.common.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

//@NoArgsConstructor : 파라미터가 없는 디폴트 생성자를 생성
//@AllArgsConstructor : 모든 필드 값을 파라미터로 받는 생성자를 생성
//@RequiredArgsConstructor : final이나 @NonNull으로 선언된 필드만을 파라미터로 받는 생성자를 생성

@Entity
@Table(name = "users")// @Document로 MongoDB 컬렉션과 매핑됨.
@Builder//사용자 정의 생성자가 있으면 안됨.
@NoArgsConstructor // 기본 생성자를 생성
@AllArgsConstructor // 모든 필드를 포함한 생성자를 생성
@Setter
@Getter
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;


    private String photo;
    private String gender;
    private String intro;
    private String residence;
    private String status;
    private String github;



}
@Embeddable
class TechStacks{
    private String front;
    private String back;
    private String mobile;
    private String etc;
}
//MongoDB에서는 스키마가 고정되어 있지 않기 때문에, @Field 어노테이션과 같은 수단을 사용하여 필드 레벨에서 직접 not null 제약 조건을 적용할 수는 없습니다
//email필드 not null조건 어떻게 추가하지 그럼??
//엔티티에는 setter사용 지양 -> 바로 DB정보가 바뀔 수 있기때문
//but DTO는 바로 DB에 적용되지 않으므로 사용.