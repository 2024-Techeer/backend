package com.example.Backend.domain.user.controllers;

import com.example.Backend.domain.user.dto.ProfileDto;
import com.example.Backend.domain.user.entities.User;
import com.example.Backend.domain.user.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/api/v1/profiles")
@RestController
public class ProfileController {

    private final ProfileService profileservice;

    public ProfileController(ProfileService profileservice){
        this.profileservice = profileservice;
    }

    @PostMapping
    public ResponseEntity<?> createProfile(@RequestBody ProfileDto profileDto){
        try{
            User user =  profileservice.createProfile(profileDto);//실제 로직은 모두 service단에서 구현
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id", user.getId()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "생성 실패"));
        }
    }

}
