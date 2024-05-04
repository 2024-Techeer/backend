package com.example.Backend.domain.recruitments.services;

import com.example.Backend.domain.common.entities.Position;
import com.example.Backend.domain.common.entities.TechStack;
import com.example.Backend.domain.common.repositories.PositionRepository;
import com.example.Backend.domain.common.repositories.TechStackRepository;
import com.example.Backend.domain.recruitments.dtos.RecruitmentCreateDto;
import com.example.Backend.domain.recruitments.entities.Recruitment;
import com.example.Backend.domain.recruitments.entities.RecruitmentPosition;
import com.example.Backend.domain.recruitments.entities.RecruitmentTechStack;
import com.example.Backend.domain.recruitments.repositorties.RecruitmentPositionRepository;
import com.example.Backend.domain.recruitments.repositorties.RecruitmentRepository;
import com.example.Backend.domain.recruitments.repositorties.RecruitmentTechStackRepository;
import com.example.Backend.domain.user.User;
import com.example.Backend.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecruitmentService {

    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private TechStackRepository techStackRepository;

    @Autowired
    private RecruitmentPositionRepository recruitmentPositionRepository;

    @Autowired
    private RecruitmentTechStackRepository recruitmentTechStackRepository;

    @Transactional
    public Recruitment createRecruitment(RecruitmentCreateDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // 유저의 이메일 추출
        User user = userRepository.findByEmail(userEmail); // 유저의 이메일을 기준으로 User 인스턴스 추출

        Recruitment recruitment = new Recruitment();
        recruitment.setType(dto.getType());
        recruitment.setTitle(dto.getTitle());
        recruitment.setNumber(dto.getNumber());
        recruitment.setStartDate(dto.getStartDate());
        recruitment.setEndDate(dto.getEndDate());
        recruitment.setDeadline(dto.getDeadline());
        recruitment.setClosing(dto.isClosing());
        recruitment.setUser(user);  // 사용자 연결

        recruitmentRepository.save(recruitment);

        // 모집글-기술분야 인스턴스들 생성
        List<Position> positions = positionRepository.findAllById(dto.getPositionIds());
        for(Position position : positions) {
            RecruitmentPosition recruitmentPosition = new RecruitmentPosition(null, recruitment, position);
            recruitmentPositionRepository.save(recruitmentPosition);
        }

        // 모집글-기술스택 인스턴스들 생성
        List<TechStack> techStacks =techStackRepository.findAllById(dto.getTechStackIds());
        for(TechStack techStack : techStacks) {
            RecruitmentTechStack recruitmentTechStack = new RecruitmentTechStack(null, recruitment, techStack);
            recruitmentTechStackRepository.save(recruitmentTechStack);
        }

        return recruitment;
    }
}
