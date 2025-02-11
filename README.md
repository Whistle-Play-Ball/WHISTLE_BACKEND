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
- Apache, Tomcat

### Collaboration Tool
깃, 노션, 디스코드, 포스트맨

<div id="4"></div>

## DATABASE
### Maria DB ERD
[Whistle Project ERD 설명](https://findthelostedhobby.tistory.com/166)
![img1.png](img%2Fimg1.png)

[//]: # (### Redis DB 구조)

<div id="5"></div>

[//]: # (## 시스템 아키텍처)

[//]: # (<div id="6"></div>)

[//]: # (## 기술 소개)

[//]: # (<div id="7"></div>)

## 팀 소개
| ![강하렴](https://avatars.githubusercontent.com/u/108250233?s=400&u=4d95286f52bc324b5a32dd231aca253297afaef8&v=4) |
|:--------------------------------------------------------------------------------------------------------------:|
|                                   [강하렴<br >(팀장)](https://github.com/kangharyeom)         |


| <center>이름</center> |       <center>역할</center>        | <center>개발 내용</center>      |어려웠던 점과 배운 점|
|:-------------------:|:--------------------------------:|-----------------------------|----|
|         강하렴       |    BackEnd & FrontEnd & Infra    | - 유저 로그인, 경기 매칭, 팀 관리 API 개발 |


<div id="8"></div>

## 이슈 정리
[팀, 매치, 리그] 생성과 [팀 리스트(팀원 목록), 매치 리스트(경기 매치 팀 리스트), 리그 리스트(리그 팀 목록)] 생성 관계
- [팀, 매치, 리그] (이하 팀으로 통일)을 생성한 유저는 팀 생성과 동시에 팀원 목록에 보여야 하므로 팀이 생성되는 단계에서 팀 리스트도 생성되어야 한다.
  - 따라서, 팀 컨트롤러 단에서 postTeam이 팀과 팀리스트를 생성하도록 구현했다.
  - [Match 테이블과 Team의 다대다 연관 관계 필요성에 관련한 문제](https://findthelostedhobby.tistory.com/154) 
  - [match와 leagueList사이에서 순환참조가 발생함](https://findthelostedhobby.tistory.com/155) 

[Spring Security] Migration 중 발생한 에러 해결
- whistle은 java 11, spring boot 2.7.14 버전으로 시작해서 java 17, spring boot 3.2.3으로 Migration했다.
- 다른 코드들은 호환되었지만 Spring Security의 일부 기능이 expired 되어서 새로운 환경에 맞는 코드로 바꿔주어야 했다.
- [Spring Security 마이그레이션 중 발생한 에러 해결](https://findthelostedhobby.tistory.com/164)
- [Spring Security 마이그레이션 중 발생한 에러 해결2](https://findthelostedhobby.tistory.com/165)

<div id="9"></div>

## Directory 구조
### BackEnd Directory 구조
```

     src
    ├── main
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

## 구현 완료된 기능 설명
[구현 완료된 기능](https://github.com/Whistle-Play-Ball/WHISTLE_BACKEND/wiki/%EA%B5%AC%ED%98%84%EB%90%9C-%EA%B8%B0%EB%8A%A5)

<div id="11"></div>

## 구현 예정 기능
[구현 예정 기능](https://github.com/Whistle-Play-Ball/WHISTLE_BACKEND/wiki/%EA%B5%AC%ED%98%84-%EC%98%88%EC%A0%95-%EA%B8%B0%EB%8A%A5)


<div id="12"></div>

## 프로젝트 업데이트 이력
#### - 2025.02.11 (project_whistle 0.75 ver) 