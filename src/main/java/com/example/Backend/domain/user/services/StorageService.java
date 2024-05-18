package com.example.Backend.domain.user.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;
import java.util.UUID;


@Service
public class StorageService {

    @Autowired
    private AmazonS3 s3Client;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;

    public String uploadFile(MultipartFile file) {
        String fileUrl="";
        try {
            File fileObj= convertMultiPartFileToFile(file);
            String fileName= generateFileName(file);
            fileUrl= "https://" + bucketName + ".s3.amazons.com/" + fileName;
            //업로드된 파일에 공개 읽기권한을 부여하는 코드
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, fileObj)
                    .withCannedAcl(CannedAccessControlList.PublicRead);

            //파일을 S3에 업로드
            s3Client.putObject(putObjectRequest);
            //로컬에 저장된 임시파일을 삭제
            fileObj.delete();
        }catch (Exception e){
            e.printStackTrace();
        }

        return fileUrl;
    }

    //MultipartFile을 File객체로 변환
    private File convertMultiPartFileToFile(MultipartFile file) throws IOException {
        File convertedFile= new File(file.getOriginalFilename());
        FileOutputStream fos= new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }

    //고유한 파일 이름 생성
    private String generateFileName(MultipartFile file) {
        return UUID.randomUUID().toString() + "-" + file.getOriginalFilename().replace(" ","_");
    }

}
