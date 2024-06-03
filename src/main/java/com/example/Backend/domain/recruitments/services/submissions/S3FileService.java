package com.example.Backend.domain.recruitments.services.submissions;

import com.amazonaws.services.s3.AmazonS3;
import com.example.Backend.global.s3.ErrorCode;
import com.example.Backend.global.s3.S3Exception;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3FileService {
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;

    public String upload(MultipartFile file){
        if(file.isEmpty() || Objects.isNull(file.getOriginalFiilename()));{
            throw new S3Exception(ErrorCode.EMPTY_FILE_EXCEPTION);
        }
        return this.uploadFile(file);
    }

    private String uploadFile(MultipartFile file){
        this.validateFileExtension(file.getOriginalFilename());
        try {
            return this.uploadFileToS3(file);
        }catch (IOException e {
            throw new S3Exception(ErrorCode.IO_EXCEPTION_ON_IMAGE_UPLOAD);
        }
    }

}
