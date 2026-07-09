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

프로젝트 개요
프로젝트명: 장애인 지원관리 시스템
프로젝트 유형: 팀 프로젝트
개발 목적
장애인 지원 업무의 전산화
역할별 업무 프로세스 분리 및 권한 기반 처리
지원계획 및 지원결과 승인 흐름 구현
첨부파일 및 수정이력 관리 기능 제공

주요기능 ,업무흐름 등 추가 내용 필요 + 팀 멤버 + 역할 + 프로젝트 기간 (6/8~7/14)

## 🖥 메인 화면 및 아키텍처 (Overview & Architecture)

### 1. 메인 화면
<!-- 메인 화면 사진 캡처 이미지 또는 동작 GIF 추가 -->
<img width="1917" height="940" alt="image" src="https://github.com/user-attachments/assets/c34f8d77-3bd6-4914-803c-b758498b3167" />



### 2. 데이터베이스 구성도 (ERD)
<!-- 데이터베이스 ERD 이미지 추가 (예: 일감, 이력, 댓글, 첨부파일 테이블 관계 등) -->
<img width="1060" height="629" alt="image" src="https://github.com/user-attachments/assets/953caaa1-de90-4f31-a5b8-70785b6ae38d" />


### 3. 시스템 흐름도 (System Flowchart)
<!-- 주요 비즈니스 로직(예: 일감 등록 -> 이력 저장 -> Toast 알림)의 흐름도 이미지 추가 -->
![시스템 흐름도](이미지_주소_여기에_입력)

<br>

## 🛠 Tech Stack (기술 스택)

### Frontend
- **Thymeleaf**: 서버 사이드 템플릿 렌더링
- **Vanilla JS**: Fetch API를 활용한 비동기 통신 및 동적 UI 제어
- **UI/UX**: Custom Toast 메시지 구현

### Backend
- **Framework**: Spring Boot / Spring MVC
- **Security**: Spring Security (인증 및 권한 처리)
- **Database**: Oracle DB (PL/SQL 프로시저 활용)
- **ORM / SQL Mapper**: JPA, MyBatis
- **Storage**: AWS S3 연동 (파일 업로드)

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



## 🚀 Trouble Shooting (문제 해결)
<!-- 해결했던 이슈 작성 -->
* **이슈:** 프로젝트 기간 밖으로 일감을 등록하거나 각종 처리에 대한 예외 처리들
* **해결:** js 조건 처리와 페이지 이동 처리 controller 등 수정
