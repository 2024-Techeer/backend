package com.example.Backend.domain.user;

import com.example.Backend.domain.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document// @Document로 MongoDB 컬렉션과 매핑됨.
@Getter
@Setter
public class User extends BaseEntity {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;

    private String profileId; // Profile 문서의 ObjectId를 참조

    public User() {
        super(); // BaseEntity의 생성자 호출
        //super() : 조상의 생성자 호출하는 것.(생성자=(인스턴스 초기화 메서드), 초기화블록은 상속되지 않기 때문.)
    }

}
