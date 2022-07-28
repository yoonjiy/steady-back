# 📖STEADY📖 - 백엔드

[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2FEFUB-steady%2Fsteady-back&count_bg=%2344D0B3&title_bg=%23CEE1C1&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false)](https://hits.seeyoufarm.com)

## 🍈 백엔드 팀원
| [김민주](https://github.com/MINJU-KIMmm)                                                                                             | [김윤지](https://github.com/siyeonkm)                                                                       | [변지은](https://github.com/hak2711)                                                                                                                 | [최빈](https://github.com/seojunglee)                                                                     |
|--------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| <img src = "https://github.com/MINJU-KIMmm/GitHubTest/blob/main/image/porkProfile/KimMinju.jpeg" width="100%"/>                                   | <img src = "https://github.com/MINJU-KIMmm/GitHubTest/blob/main/image/porkProfile/KimSiyeon.jpeg" width="98%"/>         | <img src = "https://github.com/MINJU-KIMmm/GitHubTest/blob/main/image/porkProfile/ParkHyunah.jpeg" width="100%"/>                                                 | <img src = "https://github.com/MINJU-KIMmm/GitHubTest/blob/main/image/porkProfile/LeeSeojung.jpeg" width="85%"/>        |
| [스터디] 스터디 생성, 삭제, 규칙 수정 기능</br> [신고] 신고하기, 신고 인증 취소, 신고 취소, 신고 조회 기능</br> [배포] ec2</br> [기타] 프로젝트 생성 | [스터디] 스터디 가입, 탈퇴, 벌금 정산 기능 </br> [투두리스트] 투두리스트 조회, 완료 체크 기능</br> [DB] RDS 생성 | [유저] 아이디 찾기, 임시비밀번호 전송 기능</br>[스터디] 날짜별 스터디 인증글 조회 기능</br> [DB] ERD 작성 [기타] readme 작성</br> [서버] 배포 등. | [유저] 회원 가입, 로그인, 탈퇴, 조회 기능</br> [스터디] 인증 글쓰기, 공지사항 등록 조회 기능 </br> [AWS] s3|

-------------------
## 🍊 개요
'STEADY' 는 스터디 관리를 용이하게 해주는 서비스입니다. 스터디 생성, 기한 관리, 스터디 인증글 작성, 벌금 정산, 투두리스트, 신고 등의 스터디 관련 기능을 통해 원활한 스터디 활동을 돕습니다. 스터디 인증글을 확인함으로써 스터디 부원들에게 동기부여를 해주고 스터디 관리가 부담스러워 개설하지 못하는 사람들에게 도움이 될 수 있는 효과를 기대하고 있습니다.

## 🍊 기술 스택
- DEVELOP &nbsp; 
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=round-square&logo=Spring&logoColor=white) 

- DB &nbsp; <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/> 

- AWS &nbsp;
<img src="https://img.shields.io/badge/Amazon AWS-232F3E?style=flat-square&logo=Amazon%20AWS&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon S3-569A31?style=flat-square&logo=AmazonS3&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=flat-square&logo=Amazon EC2&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon RDS-527FFF?style=flat-square&logo=Amazon RDS&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon LoadBalancer-E68B49?style=flat-square&logo=Amazon LoadBalancer&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon CodeDeploy-7D9B4B?style=flat-square&logo=Amazon CodeDeploy&logoColor=white"/>

- ETC &nbsp; 
<img src="https://img.shields.io/badge/GitHub -181717?style=flat-square&logo=GitHub&logoColor=white"/> <img src="https://img.shields.io/badge/GitHub Action-256EE0?style=flat-square&logo=GitHub Action&logoColor=white"/></br>

<img src="https://github.com/Bakery-EFUB/Bakery-Back/blob/develop/BakeryArchitectureDiagram.png"/>

## 🍊 라이브러리
1. lombok
2. spring web
3. spring data jpa
4. oauth2
5. spring boot test
6. spring session jdbc
7. spring security test

## 🍊 프로젝트 구조

### 설명
1. main/java/[프로젝트명]/config ▶️ Config
2. main/java/[프로젝트명]/domain ▶️ Entity, Repository
3. main/java/[프로젝트명]/service ▶️ Service
4. main/java/[프로젝트명]/web ▶️ Dto, Controller
5. main/java/[프로젝트명]/Application.java
6. main/resources/application.properties
7. main/resources/static/images ▶️ 리뷰에 들어가는 이미지
8. main/resources/static/uploads ▶️ 프로필 사진

### 폴더 
<pre>
<code>
└── 🗂 main
    ├── 🗂 java
    │   └── 🗂 com
    │       └── 🗂 matzipuniv
    │           └── 🗂 sinchon
    │               ├── 📑 Application.java
    │               ├── 🗂 config
    │               │   ├── 📑 CustomOAuth2UserService.java
    │               │   ├── 📑 JpaConfig.java
    │               │   ├── 📑 LoginUser.java
    │               │   ├── 📑 LoginUserArgumentResolver.java
    │               │   ├── 📑 OAuthAttributes.java
    │               │   ├── 📑 Role.java
    │               │   ├── 📑 SecurityConfig.java
    │               │   ├── 📑 SessionUser.java
    │               │   └── 📑 WebConfig.java
    │               ├── 🗂 domain
    │               │   ├── 📑 Addition.java
    │               │   ├── 📑 AdditionRepository.java
    │               │   ├── 📑 BaseTimeEntity.java
    │               │   ├── 📑 Folder.java
    │               │   ├── 📑 FolderRepository.java
    │               │   ├── 📑 Image.java
    │               │   ├── 📑 ImageRepository.java
    │               │   ├── 📑 Menu.java
    │               │   ├── 📑 MenuRepository.java
    │               │   ├── 📑 Pin.java
    │               │   ├── 📑 PinRepository.java
    │               │   ├── 📑 Report.java
    │               │   ├── 📑 ReportRepository.java
    │               │   ├── 📑 Restaurant.java
    │               │   ├── 📑 RestaurantRepository.java
    │               │   ├── 📑 Review.java
    │               │   ├── 📑 ReviewRepository.java
    │               │   ├── 📑 TempMenu.java
    │               │   ├── 📑 TempMenuRepository.java
    │               │   ├── 📑 User.java
    │               │   └── 📑 UserRepository.java
    │               ├── 🗂 service
    │               │   ├── 📑 FolderService.java
    │               │   ├── 📑 ImageService.java
    │               │   ├── 📑 MenuService.java
    │               │   ├── 📑 PinService.java
    │               │   ├── 📑 ReportService.java
    │               │   ├── 📑 RestaurantService.java
    │               │   ├── 📑 ReviewService.java
    │               │   ├── 📑 S3UploaderProfile.java
    │               │   ├── 📑 S3UploaderReview.java
    │               │   ├── 📑 TempMenuService.java
    │               │   └── 📑 UserService.java
    │               └── 🗂 web
    │                   ├── 📑 FolderApiController.java
    │                   ├── 📑 ImageApiController.java
    │                   ├── 📑 MenuController.java
    │                   ├── 📑 PinApiController.java
    │                   ├── 📑 ReportApiController.java
    │                   ├── 📑 RestaurantApiController.java
    │                   ├── 📑 ReviewApiController.java
    │                   ├── 📑 TempMenuController.java
    │                   ├── 📑 UserApiController.java
    │                   └── 🗂 dto
    │                       ├── 📑 AdditionResponseDto.java
    │                       ├── 📑 FolderResponseDto.java
    │                       ├── 📑 FolderSaveRequestDto.java
    │                       ├── 📑 ImageResponseDto.java
    │                       ├── 📑 MenuDto.java
    │                       ├── 📑 PinResponseDto.java
    │                       ├── 📑 ReportDto.java
    │                       ├── 📑 RestaurantListResponseDto.java
    │                       ├── 📑 RestaurantResponseDto.java
    │                       ├── 📑 ReviewListResponseDto.java
    │                       ├── 📑 ReviewRequestDto.java
    │                       ├── 📑 ReviewResponseDto.java
    │                       ├── 📑 TempMenuDto.java
    │                       ├── 📑 UserResponseDto.java
    │                       └── 📑 UserUpdateRequestDto.java
    └── 🗂 resources
        ├── 📑 application.properties
        └── 🗂 static
            ├── 🗂 images
            └── 🗂 uploads
</code>
</pre>


## 🍊 데이터베이스 설계도(E-R diagram)
<img src = "https://github.com/MINJU-KIMmm/GitHubTest/blob/main/image/porkProfile/matzip-univ-db.png"/>

## 🍊 API 명세서
### [🔗 Link](https://www.notion.so/API-bd2954deae834891889daaf5085d8853)

