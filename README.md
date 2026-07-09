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

# 📌 프로젝트명 (Project Name)
> **프로젝트에 대한 간단한 한 줄 설명을 작성해주세요.** 
> (예: 효율적인 협업을 위한 일감 및 테스트케이스 추적 관리 시스템)

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

## 💡 주요 구현 기능 (Key Features & Contributions)

### 1. 일감(Task) 관리 및 추적 시스템
* **일감 CRUD 및 계층 구조 관리:** 일감 등록, 수정, 삭제 및 하위 일감(Sub-task) 등록/추적 기능 구현
* **조회 권한 및 필터링 제어:** 관리자/소유자 권한에 따른 프로젝트 전체 접근 로직과 활성화된 일감(`deletedYn = 'N'`)만 필터링하여 조회하는 로직 구현
* **소요 시간 추적:** 등록된 작업 소요 시간 기록 및 관리
> <!-- 📸 일감 목록 또는 상세 화면 스크린샷 및 설명 추가 -->

### 2. 일감 항목 변경 이력 관리 (History Tracking)
* **상세 이력 테이블 설계:** 일감의 상태나 내용이 변경될 때마다 이전 값(`old_value`)과 새로운 값(`new_value`)을 기록하여 데이터 무결성과 변경 추적 지원
* **Oracle DB 최적화:** 대용량 텍스트 변경 이력도 안전하게 담을 수 있도록 이력 테이블 컬럼 사이즈 최적화 적용
> <!-- 📸 이력 조회 화면 스크린샷 및 설명 추가 -->

### 3. 비동기 댓글 및 답글 시스템
* **계층형 댓글 구조:** 일감에 대한 댓글 및 답글 등록, 수정, 삭제 기능 구현 (부모/자식 요소를 구분한 프로필 이미지 렌더링 처리)
* **Fetch API & Thymeleaf Fragment:** 댓글 작성 시 페이지 전체 새로고침 없이 Fragment를 활용하여 댓글 목록만 비동기적으로 갱신(Refresh)하도록 구현
> <!-- 📸 댓글/답글 UI 스크린샷 및 설명 추가 -->

### 4. Custom Toast 피드백 UI 개발
* **시스템 피드백 제공:** 데이터 처리, 에러 핸들링, 유효성 검사 등 사용자의 작업 결과 상태를 즉각적으로 알리는 Toast 메시지 UI 구현
* **Vanilla JS 기반:** 외부 라이브러리 의존 없이 순수 자바스크립트로 Custom Toast 함수를 개발하여 경량화 및 커스터마이징 용이성 확보
> <!-- 📸 Toast 메시지 동작 화면(GIF 등) 및 설명 추가 -->

### 5. 테스트 케이스 관리 및 일감 연동
* **테스트 케이스 CRUD:** 테스트 케이스 목록 조회 및 등록, 수정, 삭제 기능 구현
* **추적 및 매핑:** 생성된 테스트 케이스를 특정 일감과 연결하여 개발 내역과 테스트 내역을 통합 추적
> <!-- 📸 테스트 케이스 연동 화면 스크린샷 및 설명 추가 -->

### 6. 캘린더 및 마일스톤 연동
* **시각화:** 캘린더 뷰를 통해 일감의 일정 및 마일스톤 조회 기능 구현
> <!-- 📸 캘린더 화면 스크린샷 및 설명 추가 -->

### 7. 첨부파일 관리 (AWS S3)
* 일감 내 첨부파일 업로드 및 파일 버전 업데이트 관리 로직 구현
> <!-- 📸 첨부파일 영역 스크린샷 및 설명 추가 -->

<br>

## 🚀 Trouble Shooting / 핵심 기술 설명
> **이 프로젝트를 진행하면서 겪었던 문제나, 특별히 신경 써서 구현한 기술적 고민(예: MyBatis 동적 쿼리 활용, 비동기 렌더링 최적화, 암호화 처리 등)을 추가로 작성하기 좋은 공간입니다.**

* **이슈 1:** (예시) Thymeleaf Fragment와 Fetch API를 활용한 비동기 렌더링 최적화
  * **해결 과정:** ...
* **이슈 2:** ...

<br>

## ⚙️ 실행 환경 및 설치 방법 (Getting Started)
* Java Version: (예: 17)
* Build Tool: (예: Gradle or Maven)
* DB: Oracle
