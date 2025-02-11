package company.board_project.team.service;

import company.board_project.apply.entity.Apply;
import company.board_project.constant.TeamMemberRole;
import company.board_project.team.dto.TeamResponseDto;
import company.board_project.team.entity.TeamMemberInfo;
import company.board_project.team.repository.TeamMemberInfoRepository;
import company.board_project.user.repository.UserRepository;
import company.board_project.exception.BusinessLogicException;
import company.board_project.exception.Exceptions;
import company.board_project.team.entity.Team;
import company.board_project.team.repository.TeamRepository;
import company.board_project.user.entity.User;
import company.board_project.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final TeamMemberInfoRepository teamMemberInfoRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public Team createTeam(
            Team team, String email) {
        log.info("CREATE_TEAM START");

        // 유저가 회원 가입 된 유저 인지 확인
        userService.verifiedUserByEmail(email);

        // 유저 정보 조회
        User user = userService.findUserByEmail(email);

        // 유저가 팀에 가입 되어 있는지 확인
        verifyTeamExistsByUserId(user.getUserId());

        // 팀 명 중복 확인
        verifyTeamExistsByTeamName(team.getTeamName());

        try {
            // 팀에 유저 정보 삽입
            user.setTeamMemberRole(TeamMemberRole.MANAGER);
            team.setUser(user);

            userRepository.save(user);
            teamRepository.save(team);
        } catch (Exception e) {
            throw new BusinessLogicException(Exceptions.QUERY_ERROR);
        }
        return team;
    }

    public void createTeamMemberInfo(
            TeamMemberInfo teamMemberInfo, long userId, long teamId) {
        User user = userService.findUser(userId);
        Team team = findTeam(teamId);

        try {
            teamMemberInfo.setUser(user);
            teamMemberInfo.setTeam(team);
            log.info("teamMemberInfo SERVICE [{}]", teamMemberInfo.toString());
        } catch (Exception e) {
            throw new BusinessLogicException(Exceptions.QUERY_ERROR);
        }
        teamMemberInfoRepository.save(teamMemberInfo);
    }

    public Team updateTeam(
            Team team,
            Long teamId) {

        Team findTeam = findVerifiedTeam(teamId); //ID로 멤버 존재 확인하고 comment 정보 반환

        Optional.ofNullable(team.getChampionCount())
                .ifPresent(findTeam::setChampionCount);

        Optional.ofNullable(team.getMemberCount())
                .ifPresent(findTeam::setMemberCount);

        Optional.ofNullable(team.getIntroduction())
                .ifPresent(findTeam::setIntroduction);

        Optional.ofNullable(team.getAgeType())
                .ifPresent(findTeam::setAgeType);

        Optional.ofNullable(team.getLocationType())
                .ifPresent(findTeam::setLocationType);

        Optional.ofNullable(team.getSubManagerName())
                .ifPresent(findTeam::setSubManagerName);

        Optional.ofNullable(team.getUniformType())
                .ifPresent(findTeam::setUniformType);

        return teamRepository.save(findTeam);
    }

    public TeamResponseDto teamToTeamResponseDto(Team team){

        User user = team.getUser();
        List<Apply> applies = new ArrayList<>();

        // 총 승리 점수 + 무승부 점수 + 평판점수
//        String honorScore =

        return TeamResponseDto.builder()
                .userId(user.getUserId())
                .teamId(team.getTeamId())
//                .honorScore(team.getHonorScore())
                .championCount(team.getChampionCount())
                .memberCount(team.getMemberCount())
//                .leagueMatchPoints(team.getLeagueMatchPoints())
//                .leagueMatchCount(team.getLeagueMatchCount())
//                .leagueWinRecord(team.getLeagueWinRecord())
//                .leagueDrawRecord(team.getLeagueDrawRecord())
//                .leagueLoseRecord(team.getLeagueLoseRecord())
//                .totalMatchCount(team.getTotalMatchCount())
//                .totalWinRecord(team.getTotalWinRecord())
//                .totalDrawRecord(team.getTotalDrawRecord())
//                .totalLoseRecord(team.getTotalLoseRecord())
//                .honorScore(team.getHonorScore())
//                .mostGoals(team.getTeamGoals())
//                .mostAssist(team.getTeamAssist())
                .teamName(team.getTeamName())
                .applies(applies)
//                .leagueWinRecord(team.getLeagueWinRecord())
//                .leagueDrawRecord(team.getLeagueDrawRecord())
//                .leagueLoseRecord(team.getLeagueLoseRecord())
                .managerName(user.getName())
//                .leagueName(team.getLeagueName())
                .sportsType(String.valueOf(team.getSportsType()))
                .ageType(String.valueOf(team.getAgeType()))
                .locationType(String.valueOf(team.getLocationType()))
                .levelType(String.valueOf(team.getLevelType()))
                .formation(String.valueOf(team.getFormation()))
                .uniformType(String.valueOf(team.getUniformType()))
                .introduction(team.getIntroduction())
                .frequency(String.valueOf(team.getFrequency()))
                .createdAt(team.getCreatedAt())
                .modifiedAt(team.getModifiedAt())
                .build();
    }

    public Team findTeam(long teamId) {
        return findVerifiedTeam(teamId);
    }

    public Team findTeamByUserId(long userId) {
        return findVerifiedTeamByUserId(userId);
    }

    public List<Team> findAllTeamByLeagueId(long leagueId) {
        return teamRepository.findAllTeamByLeagueId(leagueId);
    }

    // 명예 점수 상위 조회
    public List<Team> findByHighestHonorScore() {
        return teamRepository.findByHighestHonorScore();
    }

    // 명예 점수 하위 조회
    public List<Team> findByLowestHonorScore() {
        return teamRepository.findByLowestHonorScore();
    }

    public Page<Team> findTeams(int page, int size) {
        return teamRepository.findAll(PageRequest.of(page, size,
                Sort.by("teamId").descending()));
    }

    public Team findTeam(int teamId) {
        return findVerifiedTeam(teamId);
    }

    public void deleteTeam(long teamId) {
        Team findTeam = findVerifiedTeam(teamId);

        teamRepository.delete(findTeam);
    }

    public Team findVerifiedTeam(long teamId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        Team findTeam =
                optionalTeam.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.TEAM_NOT_FOUND));
        return findTeam;
    }

    public Team findVerifiedTeamByUserId(long userId) {
        Optional<Team> team = teamRepository.findByUser_UserId(userId);
        Team findTeam = team.orElseThrow(() ->
                new BusinessLogicException(Exceptions.TEAM_NOT_FOUND));
        return findTeam;
    }

    public Team findTeamByTeamName(String teamName) {
        Optional<Team> optionalTeam = teamRepository.findByTeamName(teamName);
        Team findTeam =
                optionalTeam.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.TEAM_NOT_FOUND));
        return findTeam;
    }

    public TeamMemberInfo findTeamMemberListByTeamId(long teamId) {
        Optional<TeamMemberInfo> optionalTeam = teamMemberInfoRepository.findByTeam_TeamId(teamId);
        TeamMemberInfo findTeamMemberList =
                optionalTeam.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.TEAM_MEMBER_LIST_NOT_FOUND));
        return findTeamMemberList;
    }

    public TeamMemberInfo findTeamMemberListByUserId(long teamId) {
        Optional<TeamMemberInfo> optionalTeam = teamMemberInfoRepository.findByUser_UserId(teamId);
        TeamMemberInfo findTeamMemberInfo =
                optionalTeam.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.TEAM_MEMBER_LIST_NOT_FOUND));
        return findTeamMemberInfo;
    }

    public void verifyTeamExistsByUserId(long userId) {
        Optional<Team> team = teamRepository.findByUser_UserId(userId);
        if (team.isPresent()) {
            throw new BusinessLogicException(Exceptions.TEAM_EXISTS);
        }
    }

    public void verifyTeamExistsByTeamName(String teamName) {
        Optional<Team> team = teamRepository.findByTeamName(teamName);
        if (team.isPresent()) {
            throw new BusinessLogicException(Exceptions.TEAM_EXISTS);
        }
    }
}
