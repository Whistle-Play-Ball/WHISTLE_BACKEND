<div align="center">
  <br />
    <h1>휘슬</h1>
    <h2>축구 팀 매칭 플랫폼</h2>
📆2023.08.01 ~ (진행중)📆
</div>

## 목차
| <center>No</center> |<center>내용</center>|
|:-------------------:|:----:|
|        **1**        |[**💡 프로젝트 개요**](#1)
|        **2**        |[**🗒 주요 기능**](#2)
|        **3**        |[**🔍 기술 스택**](#3)
|        **4**        |[**💾 DataBase**](#4)
|        **5**        |[**📂 시스템 아키텍처**](#5) 
|        **6**        |[**📱 기술 소개**](#6)
|        **7**        |[**👪 팀 소개**](#7)
|        **8**        |[**🗂️ 이슈 정리**](#8)
|        **9**        |[**🗂️ Directory 구조**](#9)
|       **10**        |[**🗂️ 구현 완료된 기능**](#10)
|       **11**        |[**🗂️ 구현 예정 기능**](#11)
|       **12**        |[**🗂️ 프로젝트 업데이트 이력**](#12)

<div id="1"></div>

## 💡 프로젝트 개요

프로젝트 "휘슬"은 축구를 사랑하는 축구인들을 위한 팀 매칭 서비스를 제공합니다.

축구인들끼리 팀을 형성하고 더 나아가 리그를 생성하여 나만의 추억을 만들 수 있는 서비스 입니다.


<div id="2"></div>

## 주요 기능


<div id="3"></div>

## 기술 스택
### FrontEnd 
- java script 
- react
- css
- html

### BackEnd
- Java 17
- Spring Boot 3.2.3
- Gradle 8.5
- spring security 6
### DataBase 
- Redis 7.0.5
- Maria DB 10.6.15
### Infra
- Raspberry Pi

### Collaboration Tool
깃, 노션, 디스코드, 포스트맨

<div id="4"></div>

## DATABASE
### Maria DB ERD

### Redis DB 구조

<div id="5"></div>

## 시스템 아키텍처

<div id="6"></div>

## 기술 소개

<div id="7"></div>

## 팀 소개
- 프론트 엔드(강하렴) - 구현중
- 백엔드 (강하렴)

<div id="8"></div>

## 이슈 정리
[팀, 매치, 리그] 생성과 [팀 리스트(팀원 목록), 매치 리스트(경기 매치 팀 리스트), 리그 리스트(리그 팀 목록)] 생성 관계
- [팀, 매치, 리그](이하 팀으로 통일)을 생성한 유저는 팀 생성과 동시에 팀원 목록에 보여야 하므로 팀이 생성되는 단계에서 팀 리스트도 생성되어야 한다.
  - 따라서, 팀 컨트롤러 단에서 postTeam이 팀과 팀리스트를 생성하도록 구현했다.

<div id="9"></div>

## Directory 구조
### BackEnd Directory 구조
```

     src
    ├── main
    │   ├── generated
    │   │   └── company
    │   │       └── board_project
    │   │           └── domain
    │   │               ├── apply
    │   │               │   └── mapper
    │   │               │       └── ApplyMapperImpl.java
    │   │               ├── comment
    │   │               │   └── mapper
    │   │               │       └── CommentMapperImpl.java
    │   │               ├── content
    │   │               │   └── mapper
    │   │               │       └── ContentMapperImpl.java
    │   │               ├── league
    │   │               │   └── mapper
    │   │               │       └── LeagueMapperImpl.java
    │   │               ├── match
    │   │               │   ├── leagueMatch
    │   │               │   │   └── mapper
    │   │               │   │       └── LeagueMatchMapperImpl.java
    │   │               │   └── match
    │   │               │       └── mapper
    │   │               │           └── MatchMapperImpl.java
    │   │               ├── team
    │   │               │   └── mapper
    │   │               │       └── TeamMapperImpl.java
    │   │               └── user
    │   │                   └── mapper
    │   │                       └── UserMapperImpl.java
    │   ├── java
    │   │   └── company
    │   │       └── board_project
    │   │           ├── BoardProjectApplication.java
    │   │           ├── audit
    │   │           │   └── Auditable.java
    │   │           ├── aws_s3
    │   │           │   ├── AwsS3Config.java
    │   │           │   └── AwsS3Service.java
    │   │           ├── config
    │   │           │   ├── RedisConfig.java
    │   │           │   ├── SecurityConfiguration.java
    │   │           │   └── WebConfig.java
    │   │           ├── constant
    │   │           │   ├── AcceptType.java
    │   │           │   ├── AgeType.java
    │   │           │   ├── ApplyType.java
    │   │           │   ├── AuthProvider.java
    │   │           │   ├── CategoryType.java
    │   │           │   ├── Formation.java
    │   │           │   ├── Frequency.java
    │   │           │   ├── LeagueRole.java
    │   │           │   ├── LevelType.java
    │   │           │   ├── LocationType.java
    │   │           │   ├── LoginType.java
    │   │           │   ├── MatchResultStatus.java
    │   │           │   ├── MatchStatus.java
    │   │           │   ├── MatchType.java
    │   │           │   ├── Position.java
    │   │           │   ├── SeasonType.java
    │   │           │   ├── SessionConst.java
    │   │           │   ├── SportsType.java
    │   │           │   ├── TeamMemberRole.java
    │   │           │   ├── UniformType.java
    │   │           │   └── UserRole.java
    │   │           ├── domain
    │   │           │   ├── apply
    │   │           │   │   ├── controller
    │   │           │   │   │   └── ApplyController.java
    │   │           │   │   ├── dto
    │   │           │   │   │   ├── ApplyListDto.java
    │   │           │   │   │   ├── ApplyPostDto.java
    │   │           │   │   │   ├── ApplyResponseDto.java
    │   │           │   │   │   ├── LeagueApplyPostDto.java
    │   │           │   │   │   ├── LeagueApplyResponseDto.java
    │   │           │   │   │   ├── LeagueMatchApplyPostDto.java
    │   │           │   │   │   ├── LeagueMatchApplyResponseDto.java
    │   │           │   │   │   ├── MatchApplyPostDto.java
    │   │           │   │   │   ├── MatchApplyResponseDto.java
    │   │           │   │   │   ├── TeamApplyPostDto.java
    │   │           │   │   │   └── TeamApplyResponseDto.java
    │   │           │   │   ├── entity
    │   │           │   │   │   └── Apply.java
    │   │           │   │   ├── mapper
    │   │           │   │   │   └── ApplyMapper.java
    │   │           │   │   ├── repository
    │   │           │   │   │   └── ApplyRepository.java
    │   │           │   │   └── service
    │   │           │   │       └── ApplyService.java
    │   │           │   ├── comment
    │   │           │   │   ├── controller
    │   │           │   │   │   └── CommentController.java
    │   │           │   │   ├── dto
    │   │           │   │   │   ├── CommentPatchDto.java
    │   │           │   │   │   ├── CommentPostDto.java
    │   │           │   │   │   └── CommentResponseDto.java
    │   │           │   │   ├── entity
    │   │           │   │   │   └── Comment.java
    │   │           │   │   ├── mapper
    │   │           │   │   │   └── CommentMapper.java
    │   │           │   │   ├── repository
    │   │           │   │   │   └── CommentRepository.java
    │   │           │   │   └── service
    │   │           │   │       └── CommentService.java
    │   │           │   ├── content
    │   │           │   │   ├── controller
    │   │           │   │   │   └── ContentController.java
    │   │           │   │   ├── dto
    │   │           │   │   │   ├── ContentAllResponseDto.java
    │   │           │   │   │   ├── ContentListDto.java
    │   │           │   │   │   ├── ContentPatchDto.java
    │   │           │   │   │   ├── ContentPostDto.java
    │   │           │   │   │   └── ContentResponseDto.java
    │   │           │   │   ├── entity
    │   │           │   │   │   ├── Content.java
    │   │           │   │   │   └── ContentFile.java
    │   │           │   │   ├── mapper
    │   │           │   │   │   └── ContentMapper.java
    │   │           │   │   ├── repository
    │   │           │   │   │   ├── ContentFileRepository.java
    │   │           │   │   │   └── ContentRepository.java
    │   │           │   │   └── service
    │   │           │   │       └── ContentService.java
    │   │           │   ├── home
    │   │           │   │   └── HomeController.java
    │   │           │   ├── league
    │   │           │   │   ├── controller
    │   │           │   │   │   └── LeagueController.java
    │   │           │   │   ├── dto
    │   │           │   │   │   ├── LeagueListDto.java
    │   │           │   │   │   ├── LeaguePatchDto.java
    │   │           │   │   │   ├── LeaguePostDto.java
    │   │           │   │   │   └── LeagueResponseDto.java
    │   │           │   │   ├── entity
    │   │           │   │   │   ├── League.java
    │   │           │   │   │   └── LeagueParticipantsList.java
    │   │           │   │   ├── mapper
    │   │           │   │   │   └── LeagueMapper.java
    │   │           │   │   ├── repository
    │   │           │   │   │   ├── LeagueParticipantsListRepository.java
    │   │           │   │   │   └── LeagueRepository.java
    │   │           │   │   └── service
    │   │           │   │       └── LeagueService.java
    │   │           │   ├── match
    │   │           │   │   ├── leagueMatch
    │   │           │   │   │   ├── controller
    │   │           │   │   │   │   └── LeagueMatchController.java
    │   │           │   │   │   ├── dto
    │   │           │   │   │   │   ├── LeagueMatchPostDto.java
    │   │           │   │   │   │   └── LeagueMatchResponseDto.java
    │   │           │   │   │   ├── entity
    │   │           │   │   │   │   └── LeagueMatch.java
    │   │           │   │   │   ├── mapper
    │   │           │   │   │   │   └── LeagueMatchMapper.java
    │   │           │   │   │   ├── repository
    │   │           │   │   │   │   └── LeagueMatchRepository.java
    │   │           │   │   │   └── service
    │   │           │   │   │       └── LeagueMatchService.java
    │   │           │   │   └── match
    │   │           │   │       ├── controller
    │   │           │   │       │   └── MatchController.java
    │   │           │   │       ├── dto
    │   │           │   │       │   ├── MatchEndDto.java
    │   │           │   │       │   ├── MatchEndResponseDto.java
    │   │           │   │       │   ├── MatchListDto.java
    │   │           │   │       │   ├── MatchPatchDto.java
    │   │           │   │       │   ├── MatchPostDto.java
    │   │           │   │       │   └── MatchResponseDto.java
    │   │           │   │       ├── entity
    │   │           │   │       │   └── Match.java
    │   │           │   │       ├── mapper
    │   │           │   │       │   └── MatchMapper.java
    │   │           │   │       ├── repository
    │   │           │   │       │   └── MatchRepository.java
    │   │           │   │       └── service
    │   │           │   │           └── MatchService.java
    │   │           │   ├── team
    │   │           │   │   ├── controller
    │   │           │   │   │   └── TeamController.java
    │   │           │   │   ├── dto
    │   │           │   │   │   ├── TeamListDto.java
    │   │           │   │   │   ├── TeamMemberListPostDto.java
    │   │           │   │   │   ├── TeamPatchDto.java
    │   │           │   │   │   ├── TeamPostDto.java
    │   │           │   │   │   └── TeamResponseDto.java
    │   │           │   │   ├── entity
    │   │           │   │   │   ├── Team.java
    │   │           │   │   │   └── TeamMemberList.java
    │   │           │   │   ├── mapper
    │   │           │   │   │   └── TeamMapper.java
    │   │           │   │   ├── repository
    │   │           │   │   │   ├── TeamMemberListRepository.java
    │   │           │   │   │   └── TeamRepository.java
    │   │           │   │   └── service
    │   │           │   │       └── TeamService.java
    │   │           │   └── user
    │   │           │       ├── controller
    │   │           │       │   └── UserController.java
    │   │           │       ├── dto
    │   │           │       │   ├── UserListDto.java
    │   │           │       │   ├── UserPatchDto.java
    │   │           │       │   ├── UserPostDto.java
    │   │           │       │   └── UserResponseDto.java
    │   │           │       ├── entity
    │   │           │       │   └── User.java
    │   │           │       ├── mapper
    │   │           │       │   └── UserMapper.java
    │   │           │       ├── repository
    │   │           │       │   └── UserRepository.java
    │   │           │       └── service
    │   │           │           └── UserService.java
    │   │           ├── exception
    │   │           │   ├── BusinessLogicException.java
    │   │           │   ├── ErrorResponder.java
    │   │           │   ├── ErrorResponse.java
    │   │           │   └── Exceptions.java
    │   │           ├── response
    │   │           │   ├── MultiResponseDto.java
    │   │           │   ├── PageInfo.java
    │   │           │   └── SingleResponseDto.java
    │   │           ├── security
    │   │           │   ├── auth
    │   │           │   │   ├── controller
    │   │           │   │   │   └── AuthController.java
    │   │           │   │   ├── dto
    │   │           │   │   │   ├── AuthEmailConfirmDto.java
    │   │           │   │   │   ├── AuthEmailDto.java
    │   │           │   │   │   ├── AuthEmailQuestionDto.java
    │   │           │   │   │   └── AuthPasswordDto.java
    │   │           │   │   └── service
    │   │           │   │       └── AuthService.java
    │   │           │   ├── jwt
    │   │           │   │   ├── component
    │   │           │   │   │   └── JwtTokenizer.java
    │   │           │   │   ├── filter
    │   │           │   │   │   ├── JwtAuthenticationFilter.java
    │   │           │   │   │   └── JwtVerificationFilter.java
    │   │           │   │   └── handler
    │   │           │   │       ├── OAuth2UserSuccessHandler.java
    │   │           │   │       ├── UserAccessDeniedHandler.java
    │   │           │   │       ├── UserAuthenticationEntryPoint.java
    │   │           │   │       ├── UserAuthenticationFailureHandler.java
    │   │           │   │       └── UserAuthenticationSuccessHandler.java
    │   │           │   ├── login
    │   │           │   │   └── dto
    │   │           │   │       └── LoginDto.java
    │   │           │   ├── oauth
    │   │           │   │   ├── OAuth2Attributes.java
    │   │           │   │   ├── OAuth2Controller.java
    │   │           │   │   └── OAuth2UserService.java
    │   │           │   ├── userDetails
    │   │           │   │   └── MemberDetailsService.java
    │   │           │   └── utils
    │   │           │       ├── CustomAuthorityUtils.java
    │   │           │       ├── ErrorResponder.java
    │   │           │       └── RedisUtils.java
    │   │           └── util
    │   │               └── CommonUtils.java
    │   └── resources
    │       ├── application.properties
    │       ├── log4j2.xml
    │       └── templates
    │           ├── main.html
    │           └── oauth-login.html
    └── test
        └── java
            └── company
                └── board_project
                    └── BoardProjectApplicationTests.java

```


<div id="10"></div>

## 구현 완료
### 회원
회원 관련 테이블
- 회원 가입(Validation 구현(이메일, 비밀번호, 휴대폰 번호))
- 회원 정보 수정
- 회원 조회
- 회원 삭제

### 게시글
게시글 관련 테이블
- 게시글 생성
- 게시글 조회
  - 게시글 단건 조회
  - 게시글 전체 조회(pagenation)
  - 게시글 filter
    - 글 제목순 조회
    - 작성자 ID(닉네임)순 조회
    - 작성 시간순 조쇠 (최신순, 오래된 순서)

### 댓글
댓글 관련 테이블
- 댓글 생성
- 댓글 조회
  - 댓글 상세 조회
  - 댓글 전체 조회
- 댓글 삭제

### 팀
팀 관련 테이블

- 팀 정보 확인
  - 리그 ID 단위 조회

- 팀 수정
  - 팀 인원, 팀 이름, 연령대, 스포츠 타입(풋살, 축구, 그 외), 실력, 지역, 활동 빈도,
    유니폼, 포메이션, 팀 소개 수정
  - 부 매니저 수정
  - 리그 이름 수정 / leagueService / 리그 이름이 변동되면 수정됨
  - 우승 횟수 수정 / leagueService / 리그 enum이 종료처리되면 승점이 가장 큰 값을 우승처리
  - 리그 경기 수, 리그 승점, 리그 승리 수, 리그 무승부 수, 리그 패배 수, 종합 승리 수, 종합 무승부 수, 종합 패배 수, 종합 전적 수, 명예 점수
    - leagueMatchService
    - leagueMatchController 에서 리그 매치 종료 patch를 날리면 실행됨
      - homeScore && awayScore가 변동되면 변동된 값에 따라 승무패가 결정되며 승점 및 명예점수를 추가한다.
  - 매니저가 탈퇴한 경우 - 부매니저가 매니저가 될 수 있도록 (userId도 바꿔주어야함) / 부매니저는 삭제됨

### 리그
리그 관련 테이블
- 리그 생성
  - 생성과 동시에 리그 리스트도 동시에 생성
- 리그 조회
  - 명예 점수순 조회
  - 최신순 조회
  - 오래된 순서 조회
- 리그 수정
  - 모든 팀이 경기를 마치면 리그가 자동 종료된다.
  - 리그 리스트에 팀 추가시 참가한 팀 수가 추가된다.
  - 우승 횟수 수정(토너먼트 또는 리그 우승시 count++ / limit 쿼리로 구현)


### 매치
경기 매치 관련 테이블
- 매치 생성
  - 매치 매치 리스트 생성
- 매치 조회
- 매치 삭제
- 매치 정보 수정
  - 매치 점수 수정
  - 매치 결과에 따라 팀 정보 수정
    - 팀 전적 수정(승무패)
    - 리그 리스트 전적 수정(승무패)
    - 이긴 팀 명예 점수 추가(승리시 +300, 무승부시 +100, 패배시 +10)

### 리그 매치
리그 매치 관련 테이블
- 리그 매치 생성
- 리그 매치 조회
- 리그 매치 삭제
- 리그 매치 수정
  - 매치 결과에 따라 리그 정보를 수정
    - 팀 전적 수정(승무패)
    - 리그 리스트 전적 수정(승점, 승무패)
    - 이긴 팀 명예 점수 추가(승리시 +300, 무승부시 +100, 패배시 +10)

### 신청(Apply) 테이블
팀 가입 신청, 매치 신청, 리그 가입 신청 정보를 관리하는 테이블
- 팀 가입 신청
  - 팀 가입 신청 조회
  - 팀 가입 신청 내역 삭제
- 리그 가입 신청 구현 완료
  - 리그 가입 신청 조회
  - 리그 가입 신청 내역 삭제
- 매치 신청
  - 매치 신청 조회
  - 매치 신청 내역 삭제

### 팀, 리그, 매치 리스트 CRUD
리스트 테이블은 (매치, 팀, 리그) 목록 정보를 담는 테이블입니다.
- 팀 리스트 생성(팀 가입 수락)
  - 팀 리스트 조회
  - 팀 리스트 삭제
  - 팀 리스트 수정
    - away팀 생성을 수정으로 구현(matchListId를 기준으로 matchList에 있는 정보를 수정하는 것으로 구현)
    - 팀 리스트에 추가시 팀원 수 ++
- 리그 리스트 생성(리그 가입 수락)
  - 리그 리스트 조회
    - 리그 순위 조회 (승점을 orderby DESC로 구현)
  - 리그 리스트 삭제
- 매치 리스트 생성(매치 신청 수락)
  - 매치 리스트 조회
  - 매치 리스트 삭제


### 보안, 배포, DB관련 기능

#### JWT
- JWT Tolenizer

#### Redis
- login, logOut 구현
- 고가용성을 위한 master, replica 서버 구성 및 sentinel 구성
  - master 1 : slave 2 : sentinel 3

#### Oauth 2.0
- google login 구현



<div id="11"></div>

## 구현 예정 기능 - 2023.09.25
완료된 기능은 구현 하단의 구현 완료로 이동함

### 회원
회원 관련 테이블
- 마이페이지
  - 내가 쓴 게시글
  - 내 팀 정보
  - 경기 일정
  - 경기 결과 모음
    - 리그 경기 
    - 팀 경기
    - 용병으로 참여한 경기

### 게시글
게시글 관련 테이블
- 파일 업로드
- 게시판 카테고리
  - 용병 모집 게시판

### 댓글
댓글 관련 테이블

### 팀
팀 관련 테이블
- 팀 로고 파일 업로드
- 팀 수정
  - 리그 이름 수정 / leagueService / 리그 이름이 변동되면 수정됨 
  - 우승 횟수 수정 / leagueService / 리그 enum이 종료처리되면 승점이 가장 큰 값을 우승처리
  - 매니저가 탈퇴한 경우 - 부매니저가 매니저가 될 수 있도록 (userId도 바꿔주어야함) / 부매니저는 삭제됨

### 매치
매치 관련 테이블

### 리그
리그 관련 테이블
- 리그 로고 파일 업로드
- 리그 생성
  - 리그 생성시 팀 정보에 리그 이름 변경
- 리그 수정
  - 리그 명예 점수 수정(팀 명예점수 종합)


### 신청(Apply) 테이블
팀 가입 신청, 매치 신청, 리그 가입 신청 정보를 관리하는 테이블

### 팀, 매치, 리그 리스트
팀, 매치, 리그 목록 정보를 담는 테이블
- 팀
 
- 매치
  - 
- 리그
  - 리그 조회
    - 랭킹 조회 (쿼리로 구현 / 명예점수를 종합하여 DESC순으로 구현)

    - 리그 리스트에 추가시 팀 정보에서 리그 이름 변경

### 그 외 기능
- 로그 찍기
- 파일 업로드(같은 파일 이름으로 처리되지 않도록 구현 - UUID 고려해보기)
- 로그인(Oauth 2.0)
- 신청
- 팀, 리그, 매치 리스트 CRUD

### @챌린지
- 팀, 리그 개인 기록
- MOM, GOAL, ASSIST
- 팀 신청시 메일 보내기
- 유저 정보 마스킹



<div id="12"></div>

## 프로젝트 업데이트 이력
#### - 2024.06.10 (project_whistle 0.7 ver) 