package com.example.Backend.domain.user.services;

import com.example.Backend.domain.common.entities.Position;
import com.example.Backend.domain.common.entities.TechStack;
import com.example.Backend.domain.common.repositories.PositionRepository;
import com.example.Backend.domain.common.repositories.TechStackRepository;
import com.example.Backend.domain.user.dto.profiles.ProfileDto;
import com.example.Backend.domain.user.dto.profiles.ProfileUpdateDto;
import com.example.Backend.domain.user.dto.profiles.ProfileViewDto;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private S3ImageService s3ImageService;

    //프로필도 USER엔티티에서 가져오는 것. 프로필 테이블이 따로 없음.
    @Transactional
    public User createProfile(ProfileDto profileDto, MultipartFile photoFile) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email :" + userEmail));

        // 사진 파일이 있으면 S3에 업로드하고 URL을 설정
        if (!photoFile.isEmpty()) {
            String photoUrl = s3ImageService.upload(photoFile);
            System.out.println(photoUrl);// S3 업로드 후 URL 반환
            user.setPhoto(photoUrl);
        }

        user.setGender(profileDto.getGender());
        user.setIntro(profileDto.getIntro());
        user.setResidence(profileDto.getResidence());
        user.setStatus(profileDto.getStatus());
        user.setGithub(profileDto.getGithub());
        userRepository.save(user);//일단 먼저 저장

        //모집글 - 기술분야 인스턴스들 생성
        List<Position> positions = positionRepository.findAllById(profileDto.getPositionIds());
        /*
        * 여기서 profileDto.getPositionIds()는 ProfileDto 객체가 가지고 있는 포지션 ID들의 목록을 리턴합니다. 이 메소드는 일반적으로 사용자가 입력한, 혹은 선택한 포지션 ID들을 포함하고 있습니다.
        * positionRepository.findAllById(...) 메소드는 Spring Data JPA의 JpaRepository 인터페이스에서 제공하는 메소드로, 여러 ID 값을 받아 해당하는 모든 엔티티를 데이터베이스에서 찾아 리스트로 반환합니다.
        * 이는 일대다 또는 다대다 관계에서 유용하게 사용됩니다, 예를 들어 사용자 프로필에 여러 포지션을 연결할 때 필요합니다.
        * */
        for (Position position : positions) {
            UserPosition userPosition = new UserPosition(null,user,position);
            userPositionRepository.save(userPosition);
        }
        List<TechStack> techStacks= techStackRepository.findAllById(profileDto.getTechStackIds());
        for (TechStack techStack : techStacks) {
            UserTechStack userTechStack = new UserTechStack(null,user,techStack);
            userTechStackRepository.save(userTechStack);
        }
        return user;
    }

    public ProfileViewDto getProfile(Long userId){
        User user = userRepository.findById(userId).orElseThrow(()->new UsernameNotFoundException("User not found with id :" +userId));
        return convertToProfileViewDto(user);
    }

    @Transactional
    public User updateProfile(Long userId, ProfileUpdateDto profileUpdateDto){
        User user=userRepository.findById(userId).orElseThrow(()->new UsernameNotFoundException("User not found with id :" +userId));

        updateProfileData(user, profileUpdateDto);
        userRepository.save(user);
        return user;
    }

    public void deleteProfile(Long userId){
        userRepository.deleteById(userId);
    }
    private ProfileViewDto convertToProfileViewDto(User user){
        ProfileViewDto profileViewDto = new ProfileViewDto();
        profileViewDto.setPhoto(user.getPhoto());
        profileViewDto.setGender(user.getGender());
        profileViewDto.setIntro(user.getIntro());
        profileViewDto.setResidence(user.getResidence());
        profileViewDto.setStatus(user.getStatus());
        profileViewDto.setGithub(user.getGithub());

        // Position 이름 추출
        List<String> positionNames = userPositionRepository.findByUser(user)
                .stream()
                .map(recruitmentPosition -> recruitmentPosition.getPosition().getName())
                .collect(Collectors.toList());
        profileViewDto.setPositions(positionNames);

        // TechStack 이름 추출
        List<String> techStackNames = userTechStackRepository.findByUser(user)
                .stream()
                .map(recruitmentTechStack -> recruitmentTechStack.getTechStack().getName())
                .collect(Collectors.toList());
        profileViewDto.setTechStacks(techStackNames);

        return profileViewDto;
    }

    private void updateProfileData(User user, ProfileUpdateDto profileUpdateDto){
        user.setPhoto(profileUpdateDto.getPhoto());
        user.setGender(profileUpdateDto.getGender());
        user.setIntro(profileUpdateDto.getIntro());
        user.setResidence(profileUpdateDto.getResidence());
        user.setStatus(profileUpdateDto.getStatus());
        user.setGithub(profileUpdateDto.getGithub());
    }
}
/*
* 코드에서 UserPosition 객체를 저장할 때, JPA는 user와 position 객체의 ID를 추출하여 user_id와 position_id 외래 키 필드에 저장합니다.
* 이를 통해 데이터베이스의 무결성과 객체 간 관계를 유지할 수 있습니다.
객체를 조회할 때, JPA는 user_id와 position_id를 기반으로 연관된 User와 Position 객체를 자동으로 로드하여 UserPosition 객체의 user와 position 필드에 할당합니다.
* */
