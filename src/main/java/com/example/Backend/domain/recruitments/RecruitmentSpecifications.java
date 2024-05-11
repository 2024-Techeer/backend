package com.example.Backend.domain.recruitments;

import com.example.Backend.domain.recruitments.entities.Recruitment;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class RecruitmentSpecifications {

    public static Specification<Recruitment> hasType(String type){
        return(root, query,cb) -> {
            if(type == null)
                return null;
            return cb.equal(root.get("type"), type);
        };
    }

    public static Specification<Recruitment> hasPosition(Long positionId){
        return (root, query, cb) -> {
            if(positionId == null)
                return null;
            return cb.equal(root.get("position"), positionId);
        };
    }

    public static Specification<Recruitment> hasTechStack(Long techStackId){
        return (root,query,cb) -> {
            if(techStackId == null)
                return null;
            return cb.equal(root.get("techStack"), techStackId);
        };
    }
}
