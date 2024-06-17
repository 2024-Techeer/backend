package com.example.Backend.domain.user.dto.profiles;

import lombok.Data;
import java.util.List;

@Data
public class ProfileViewDto {
    private Long id;
    private String photo;
    private String gender;
    private String intro;
    private String residence;
    private String status;
    private List<String> positions;
    private List<String> techStacks;
    private String github;

}
