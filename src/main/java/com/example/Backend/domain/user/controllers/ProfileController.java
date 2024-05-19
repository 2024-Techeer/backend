package com.example.Backend.domain.user.controllers;

import com.example.Backend.domain.user.dto.profiles.ProfileDto;
import com.example.Backend.domain.user.dto.profiles.ProfileUpdateDto;
import com.example.Backend.domain.user.dto.profiles.ProfileViewDto;
import com.example.Backend.domain.user.entities.User;
import com.example.Backend.domain.user.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RequestMapping("/api/v1/profiles")
@RestController
public class ProfileController {

    private final ProfileService profileservice;

    @Autowired
    public ProfileController(ProfileService profileservice){

        this.profileservice = profileservice;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createProfile(@RequestBody ProfileDto profileDto, @RequestPart("photo") MultipartFile photo){
        try{
            User user =  profileservice.createProfile(profileDto, photo);//실제 로직은 모두 service단에서 구현
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id", user.getId()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "생성 실패"));
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getProfile(@PathVariable Long userId){

        try{
            ProfileViewDto profile = profileservice.getProfile(userId);
            return ResponseEntity.ok(profile);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Profile not found: " +e.getMessage()));
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateProfile(@PathVariable Long userId, @RequestBody ProfileUpdateDto profileUpdateDto){
        try{
            User updatedUser = profileservice.updateProfile(userId, profileUpdateDto);
            return ResponseEntity.ok(updatedUser);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Failed to update profile: " +e.getMessage()));
        }
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long userId){
        try{
            profileservice.deleteProfile(userId);
            return ResponseEntity.ok().build();

        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Failed to delete profile: "+e.getMessage()));
        }
    }


}
