package com.example.Backend.domain.recruitments.controllers;

import com.example.Backend.domain.recruitments.dtos.recruitments.RecruitmentCreateDto;
import com.example.Backend.domain.recruitments.dtos.recruitments.RecruitmentDetailDto;
import com.example.Backend.domain.recruitments.dtos.recruitments.RecruitmentReadDto;
import com.example.Backend.domain.recruitments.dtos.recruitments.RecruitmentUpdateDto;
import com.example.Backend.domain.recruitments.entities.recruitments.Recruitment;
import com.example.Backend.domain.recruitments.services.recruitments.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/recruitments")
public class RecruitmentsController {

    @Autowired
    private RecruitmentService recruitmentService;

    // 모집글 생성
    @PostMapping
    public ResponseEntity<?> createRecruitment(@RequestBody RecruitmentCreateDto dto) {
        try {
            Recruitment recruitment = recruitmentService.createRecruitment(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("recruitmentId", recruitment.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "생성 실패"));
        }
    }

    // 모집글 전체 조회
    @GetMapping
    public ResponseEntity<List<RecruitmentReadDto>> getAllRecruitments(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Long positionId,
            @RequestParam(required = false) Long techStackId) {

        List<RecruitmentReadDto> recruitments = recruitmentService.filterRecruitments(type, positionId, techStackId);

        // 결과가 비어있는지 확인 후 적절한 상태 코드와 함께 반환
        if (recruitments.isEmpty()) {
            return ResponseEntity.noContent().build();  // 내용이 없을 경우 No Content 상태 반환
        } else {
            return ResponseEntity.ok(recruitments);  // 내용이 있을 경우 OK 상태와 함께 데이터 반환
        }
    }

    // 모집글 상세 조회
    @GetMapping("/{recruitmentId}")
    public ResponseEntity<RecruitmentDetailDto> getRecruitmentById(@PathVariable Long recruitmentId) {
        return recruitmentService.getRecruitmentById(recruitmentId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 모집글 수정
    @PatchMapping("/{recruitmentId}")
    public ResponseEntity<String> updateRecruitment(@PathVariable Long recruitmentId, @RequestBody RecruitmentUpdateDto dto) {
        Recruitment updatedRecruitment = recruitmentService.updateRecruitment(recruitmentId, dto);
        String responseMessage = "수정 완료: " + updatedRecruitment.getId();  // ID를 포함하는 메시지 생성
        return ResponseEntity.ok(responseMessage);  // 메시지 반환
    }

    // 모집글 삭제
    @DeleteMapping("/{recruitmentId}")
    public ResponseEntity<?> deleteRecruitment(@PathVariable Long recruitmentId) {
        recruitmentService.deleteRecruitment(recruitmentId);
        return ResponseEntity.noContent().build();  // 204 No Content response
    }

    // 모집글 마감
    @PatchMapping("/{recruitmentId}/closing")
    public ResponseEntity<String> closeRecruitment(@PathVariable Long recruitmentId) {
        recruitmentService.closeRecruitment(recruitmentId);
        String responseMessage = "마감 완료: " + recruitmentId;
        return ResponseEntity.ok(responseMessage);
    }
}
