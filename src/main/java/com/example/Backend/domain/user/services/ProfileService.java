package com.example.Backend.domain.user.services;

import com.example.Backend.domain.common.entities.Position;
import com.example.Backend.domain.common.entities.TechStack;
import com.example.Backend.domain.common.repositories.PositionRepository;
import com.example.Backend.domain.common.repositories.TechStackRepository;
import com.example.Backend.domain.recruitments.repositorties.RecruitmentRepository;
import com.example.Backend.domain.user.dto.ProfileDto;
import com.example.Backend.domain.user.entities.User;
import com.example.Backend.domain.user.entities.UserPosition;
import com.example.Backend.domain.user.entities.UserTechStack;
import com.example.Backend.domain.user.repositories.UserPositionRepository;
import com.example.Backend.domain.user.repositories.UserRepository;
import com.example.Backend.domain.user.repositories.UserTechStackRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    @Autowired
    private UserPositionRepository userPositionRepository;
    @Autowired
    private UserTechStackRepository userTechStackRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private TechStackRepository techStackRepository;

    //프로필도 USER엔티티에서 가져오는 것. 프로필 테이블이 따로 없음.
    @Transactional
    public User createProfile(ProfileDto profileDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();//이메일 반환되는 것.
        User user=userRepository.findByEmail(userEmail)
                .orElseThrow(()->new UsernameNotFoundException("User not found with email :" +userEmail));

        user.setPhoto(profileDto.getPhoto());
        user.setGender(profileDto.getGender());
        user.setIntro(profileDto.getIntro());
        user.setResidence(profileDto.getResidence());
        user.setStatus(profileDto.getStatus());
        userRepository.save(user);//일단 먼저 저장

        //모집글 - 기술분야 인스턴스들 생성
        List<Position> positions = positionRepository.findAllById(profileDto.getPositionIds());
        for (Position position : positions) {
            UserPosition userPosition = new UserPosition();
            userPositionRepository.save(userPosition);
        }
        List<TechStack> techStacks= techStackRepository.findAllById(profileDto.getTechStackIds());
        for (TechStack techStack : techStacks) {
            UserTechStack userTechStack = new UserTechStack();
            userTechStackRepository.save(userTechStack);
        }
        return user;
    }
}
