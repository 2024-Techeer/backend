package com.example.Backend.domain.common;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

public class BaseEntity {
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;

    private Date deletedAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}
//BaseEntity 사용 이유 : 공통 필드인 createdAt, updatedAt, deletedAt를 BaseEntity 클래스로 분리하여 재사용할 수 있다.
// 이렇게 하면 다른 도메인 모델에서도 이러한 공통 속성을 쉽게 상속받아 사용할 수 있다.
