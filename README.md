# WEPLE : 함께 만드는 연결된 협업 플랫폼
## 팀 구성 및 역할

<table align="center">
  <tr>
    <td align="center">
      <a href="https://github.com/time1014">
        <img src="https://avatars.githubusercontent.com/u/64236748?v=4" width="100px;" alt="방진영"/><br />
        <sub><b>방진영</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/crescentia0011">
        <img src="https://avatars.githubusercontent.com/u/254889839?v=4" width="100px;" alt="김병완"/><br />
        <sub><b>김병완</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/kimeunji806">
        <img src="https://avatars.githubusercontent.com/u/258710580?v=4" width="100px;" alt="김은지"/><br />
        <sub><b>김은지</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/kimminji28">
        <img src="https://github.com/kimminji28.png" width="100px;" alt="김민지"/><br />
      <sub><b>김민지</b></sub>
        </a>
    </td>
    <td align="center">
      <a href="https://github.com/smk412">
       <img src="https://github.com/smk412.png" width="100px;" alt="송민규"/><br />
      <sub><b>송민규</b></sub>
        </a>
    </td>
  </tr>

  <tr>
    <th align="center">팀장</th>
    <th align="center">부팀장</th>
    <th align="center">팀원</th>
    <th align="center">팀원</th>
    <th align="center">팀원</th>
  </tr>

  <tr>
    <td align="center">프로젝트 총괄</td>
    <td align="center">배포</td>
    <td align="center">DB</td>
    <td align="center">개발환경</td>
    <td align="center">Git</td>
  </tr>
</table>

# 📌 Spring을 활용한 클라우드 기반 프로젝트 관리 시스템 (WEPLE)
> **레드마인을 기반으로 한 사용자들의 일정관리와 일감 등록을 통합 관리하는 협업툴 서비스**

<br>

프로젝트 목표
WEPLE은 프로젝트 수행에 필요한 업무 관리, 일정 관리, 산출물 관리, 협업 기능을 하나의 플랫폼에서 제공하는 것을 목표로 합니다.
프로젝트 구성원은 일감과 일정, 파일, 위키, 저장소 이력을 함께 확인할 수 있고, 관리자는 사용자와 권한, 프로젝트 설정을 통합적으로 관리할 수 있습니다.

프로젝트 기간 
2026/06/08 ~ 2026/07/15

팀 구성
이름	  주요 담당
방진영	  일감, 테스트 케이스, 캘린더
김병완	  로그인 및 인증, 사용자관리, 저장소 연동, 배포
김은지	  프로젝트, 위키, 알림, 마이페이지, 칸반보드
김민지	  그룹, 코드값, 소요시간, 파일관리
송민규	  공통 UI, 대시보드, 간트차트 프로젝트 보조 기능

## 주요 기능

### 관리

- 회사별 사용자 가입승인
- 사용자 등록 및 계정 상태 관리
- 그룹 관리
- 역할 및 권한 관리
- 코드값 및 서비스 설정 관리

### 프로젝트

- 프로젝트 생성 및 관리
- 프로젝트 구성원 등록 및 관리
- 프로젝트별 모듈 설정
- 버전 및 마일스톤 관리
- 로드맵 관리

### 일감 / 테스트

- 일감 등록, 조회, 수정, 삭제
- 일감 댓글 및 작업내역 관리
- 일감 유형, 우선순위, 상태 관리
- 테스트 케이스 관리
- 요구사항 커버리지 확인

### 일정 / 현황

- 통합 캘린더 및 프로젝트 캘린더
- 작업 시간 등록 및 소요시간 관리
- 간트차트
- 칸반보드
- 프로젝트 진행 현황 확인

### 협업 / 산출물

- 위키 등록 및 관리
- 게시판
- 파일 업로드 및 다운로드
- 다운로드 이력 조회
- 알림 및 마이페이지

### 저장소

- GitHub 저장소 등록 및 관리
- 저장소 파일트리 및 파일 내용 조회
- 커밋 내역 조회
- 커밋 상세 및 변경 내역 확인
- 커밋 메시지 기반 일감 연결


## 🖥 메인 화면 및 아키텍처 (Overview & Architecture)

### 1. 메인 화면
<!-- 메인 화면 사진 캡처 이미지 또는 동작 GIF 추가 -->
<img width="1917" height="940" alt="image" src="https://github.com/user-attachments/assets/c34f8d77-3bd6-4914-803c-b758498b3167" />



### 2. 데이터베이스 구성도 (ERD)
<!-- 데이터베이스 ERD 이미지 추가 (예: 일감, 이력, 댓글, 첨부파일 테이블 관계 등) -->



### 3. 시스템 흐름도 (System Flowchart)
<!-- 주요 비즈니스 로직(예: 일감 등록 -> 이력 저장 -> Toast 알림)의 흐름도 이미지 추가 -->
![시스템 흐름도](이미지_주소_여기에_입력)

<br>

## 기술 스택

### Backend

- Java 21
- Spring Boot 3.5.16
- Spring MVC
- Spring Security
- MyBatis
- JPA
- Oracle Database
- Jasypt

### Frontend

- Thymeleaf
- JavaScript
- HTML5
- CSS3

### External / Infra

- GitHub REST API
- AWS S3
- AWS EC2
- Docker
- Jenkins
- GitHub Actions

<br>

## 💡 주요 구현 기능 (Key Features)

### 1. 일감(Task) 관리 및 추적 시스템
* 일감 등록, 수정, 삭제 및 하위 일감(Sub-task) 추적
* 삭제 여부(`deletedYn = 'N'`) 기반 필터링 및 소요 시간 기록
* 첨부파일 AWS S3
<img width="1640" height="678" alt="일감 목록 일감" src="https://github.com/user-attachments/assets/69ec1efe-0484-4dcf-8ed5-58b7af782539" />

### 2. 일감 항목 변경 이력 관리 (History Tracking)
* 일감 내용 수정 시 `old_value`와 `new_value`를 기록하여 변경 추적
* 일감 내용 등록,수정 , 소요시간 등록 , 상위일감 연결 등 일감 관련 변경사항 기록 
<img width="1505" height="830" alt="일감 항목 변경이력" src="https://github.com/user-attachments/assets/a89b028d-948d-4aa8-9b51-44b4149c5bf4" />

### 3. 비동기 댓글 및 답글 시스템
* 부모/자식 계층형 댓글 구조 및 프로필 이미지 렌더링
* Fetch API와 Thymeleaf Fragment를 활용한 비동기 댓글 리프레시
<img width="1342" height="694" alt="삭제된 댓글 포함" src="https://github.com/user-attachments/assets/fd5767b5-273d-43b5-a7a8-2066211f11b2" />
<img width="724" height="222" alt="image" src="https://github.com/user-attachments/assets/6fb0e278-018c-44c5-bd85-648f349a4a22" />

### 4. Custom Toast 피드백 UI
* 사용자 작업(저장, 수정, 에러 등) 결과를 즉각적으로 알리는 커스텀 피드백 UI
* 댓글 내용 미작성 후 등록 , 댓글 등록 후 , 수정 , 삭제 등등에서 사용
<img width="302" height="96" alt="댓글 등록 성공" src="https://github.com/user-attachments/assets/58ad3f27-8a93-46c3-9bac-eb2df43f1761" />

### 5. 테스트 케이스 및 기타 기능
* 테스트 케이스 생성 및 일감 연동 및 추적
<img width="1664" height="829" alt="테스트 케이스 목록" src="https://github.com/user-attachments/assets/1d5aff65-dc75-42bd-a041-34ec389f2be6" />

### 6. 캘린더 일정 확인
* 캘린더를 통한 일감/마일스톤 시각화 및 추적
<img width="1686" height="808" alt="캘린더" src="https://github.com/user-attachments/assets/3761984d-111f-4218-840f-15e8e7860304" />
<img width="713" height="641" alt="캘린더 상세 모달" src="https://github.com/user-attachments/assets/587b8dd5-16d7-4601-8b61-332b36fcee73" />

<br>


## 🛠 기술 스택 (Tech Stack)

| 구분 | 사용 기술 |
|------|----------|
| **Frontend** | ![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white) ![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white) ![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black) ![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=Thymeleaf&logoColor=white) |
| **Backend** | ![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white) ![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white) |
| **Data & ORM** | ![Oracle](https://img.shields.io/badge/Oracle-F00000?style=for-the-badge&logo=oracle&logoColor=white) ![JPA](https://img.shields.io/badge/JPA-59666C?style=for-the-badge) ![MyBatis](https://img.shields.io/badge/MyBatis-000000?style=for-the-badge) |
| **Cloud & Storage**| ![AWS S3](https://img.shields.io/badge/Amazon_S3-569A31?style=for-the-badge&logo=amazons3&logoColor=white) |
| **Collaboration** | ![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white) ![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white) |

실행 방법

1. 환경 변수 설정
실행 환경에 맞게 필요한 값을 설정합니다.
JASYPT_PASSWORD
GITHUB_API_TOKEN
AWS_ACCESS_KEY_ID
AWS_SECRET_ACCESS_KEY

2. 애플리케이션 실행
./mvnw spring-boot:run
Windows 환경에서는 다음 명령을 사용할 수 있습니다.
mvnw.cmd spring-boot:run

3. 빌드
./mvnw clean package
배포 구조
WEPLE은 GitHub, Jenkins, Docker, DockerHub, AWS EC2를 기반으로 배포 환경을 구성했습니다.
GitHub push
→ GitHub Actions
→ Jenkins 원격 빌드 실행
→ Maven Build
→ Docker Image Build
→ DockerHub Push
→ 운영 EC2에서 Docker Image Pull & Run

## 🚀 Trouble Shooting (문제 해결)
<!-- 해결했던 이슈 작성 -->
* **이슈:** 프로젝트 기간 밖으로 일감을 등록하거나 각종 처리에 대한 예외 처리들
* **해결:** js 조건 처리와 페이지 이동 처리 controller 등 수정
