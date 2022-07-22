package com.steady.steadyback.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.steady.steadyback.domain.*;
import com.steady.steadyback.dto.StudyPostRequestDto;
import com.steady.steadyback.dto.StudyPostResponseDto;
import com.steady.steadyback.util.errorutil.CustomException;
import com.steady.steadyback.util.errorutil.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Component
public class StudyPostService {

    private final StudyRepository studyRepository;
    private final UserRepository userRepository;
    private final StudyPostRepository studyPostRepository;

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;  // S3 버킷 이름

    public StudyPostResponseDto createStudyPost(StudyPostRequestDto studyPostRequestDto, List<MultipartFile> multipartFiles) throws IOException {

        int imageCount = 0;
        for (MultipartFile file : multipartFiles) {
            if (!file.isEmpty())
                imageCount++;
        }
        if(studyPostRequestDto.getLink().isEmpty() && imageCount == 0) {
            throw new CustomException(ErrorCode.CANNOT_EMPTY_CONTENT);
        }
        if(imageCount > 2)
            throw new CustomException(ErrorCode.OVER_2_IMAGES);

        User user = studyPostRequestDto.getUser();
        userRepository.findById(user.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        Study study = studyPostRequestDto.getStudy();
        studyRepository.findById(study.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.STUDY_NOT_FOUND));


        StudyPost studyPost = studyPostRepository.save(studyPostRequestDto.toEntity());

        List<String> uploadImageUrl = new ArrayList<>();

        if(imageCount > 0) {
            for (MultipartFile file : multipartFiles) {
                //사진 없으면 계속 여기서 에러 뜸
                File uploadFile = convert(file)  // 파일 변환할 수 없으면 에러
                        .orElseThrow(() -> new IllegalArgumentException("error: MultipartFile -> File convert fail"));

                uploadImageUrl.add(upload(uploadFile, "static"));
            }
        }

        StudyPostResponseDto studyPostResponseDto = new StudyPostResponseDto(studyPost.getId(), studyPost.getUser().getId(), studyPost.getStudy().getId(), studyPost.getLink(), uploadImageUrl, studyPost.getDate());

        return studyPostResponseDto;
    }


    // S3로 파일 업로드하기
    private String upload(File uploadFile, String dirName) {
        String fileName = dirName + "/" + UUID.randomUUID() + uploadFile.getName();   // S3에 저장된 파일 이름
        String uploadImageUrl = putS3(uploadFile, fileName); // s3로 업로드
        removeNewFile(uploadFile);
        return uploadImageUrl;
    }

    // S3로 업로드
    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    // 로컬에 저장된 이미지 지우기
    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("File delete success");
            return;
        }
        log.info("File delete fail");
    }

    // 로컬에 파일 업로드 하기
    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(System.getProperty("user.dir") + "/" + file.getOriginalFilename());
        if (convertFile.createNewFile()) { // 바로 위에서 지정한 경로에 File이 생성됨 (경로가 잘못되었다면 생성 불가능)
            try (FileOutputStream fos = new FileOutputStream(convertFile)) { // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기 위함
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }

        return Optional.empty();
    }

}
