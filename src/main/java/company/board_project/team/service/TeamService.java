package company.board_project.team.service;

import company.board_project.constant.TeamMemberRole;
import company.board_project.team.entity.TeamMemberList;
import company.board_project.team.repository.TeamMemberListRepository;
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

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final TeamMemberListRepository teamMemberListRepository;
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

        try {
            // 팀에 유저 정보 삽입
            user.setTeamMemberRole(TeamMemberRole.MANAGER);
            team.setUser(user);
            team.setManagerName(user.getName());

            userRepository.save(user);
            teamRepository.save(team);
        } catch (Exception e) {
            throw new BusinessLogicException(Exceptions.QUERY_ERROR);
        }
        return team;
    }

    public void createTeamMemberList(
            TeamMemberList teamMemberList, long userId, long teamId) {
        User user = userService.findUser(userId);
        Team team = findTeam(teamId);

        try {
            teamMemberList.setUser(user);
            teamMemberList.setTeam(team);
            log.info("teamMemberList SERVICE [{}]", teamMemberList.toString());
        } catch (Exception e) {
            throw new BusinessLogicException(Exceptions.QUERY_ERROR);
        }
        teamMemberListRepository.save(teamMemberList);
    }

    public Team updateTeam(
            Team team,
            Long teamId) {

        Team findTeam = findVerifiedTeam(teamId); //ID로 멤버 존재 확인하고 comment 정보 반환

        Optional.ofNullable(team.getChampionCount())
                .ifPresent(findTeam::setChampionCount);

        Optional.ofNullable(team.getMemberCount())
                .ifPresent(findTeam::setMemberCount);

        Optional.ofNullable(team.getLeagueWinRecord())
                .ifPresent(findTeam::setLeagueWinRecord);

        Optional.ofNullable(team.getLeagueDrawRecord())
                .ifPresent(findTeam::setLeagueDrawRecord);

        Optional.ofNullable(team.getLeagueLoseRecord())
                .ifPresent(findTeam::setLeagueLoseRecord);

        Optional.ofNullable(team.getTotalWinRecord())
                .ifPresent(findTeam::setTotalWinRecord);

        Optional.ofNullable(team.getTotalDrawRecord())
                .ifPresent(findTeam::setTotalDrawRecord);

        Optional.ofNullable(team.getTotalLoseRecord())
                .ifPresent(findTeam::setTotalLoseRecord);

        Optional.ofNullable(team.getHonorScore())
                .ifPresent(findTeam::setHonorScore);

        Optional.ofNullable(team.getTeamGoals())
                .ifPresent(findTeam::setTeamGoals);

        Optional.ofNullable(team.getTeamAssist())
                .ifPresent(findTeam::setTeamAssist);

        Optional.ofNullable(team.getIntroduction())
                .ifPresent(findTeam::setIntroduction);

        Optional.ofNullable(team.getAgeType())
                .ifPresent(findTeam::setAgeType);

        Optional.ofNullable(team.getLocationType())
                .ifPresent(findTeam::setLocationType);

        Optional.ofNullable(team.getManagerName())
                .ifPresent(findTeam::setManagerName);

        Optional.ofNullable(team.getSubManagerName())
                .ifPresent(findTeam::setSubManagerName);

        Optional.ofNullable(team.getUniformType())
                .ifPresent(findTeam::setUniformType);

        return teamRepository.save(findTeam);
    }

    public Team updateForMatchEnd(
            Long homeTeamScore
            , Long awayTeamScore
            , Long homeTeamId
            , Long awayTeamId
    ) {
        Team findHomeTeam = findVerifiedTeam(homeTeamId);
        Team findAwayTeam = findVerifiedTeam(awayTeamId);
        if (homeTeamScore > awayTeamScore) {
            findHomeTeam.setHonorScore(findHomeTeam.getHonorScore() + 300);
            findHomeTeam.setTotalWinRecord(findHomeTeam.getTotalWinRecord() + 1);

            findAwayTeam.setHonorScore(findAwayTeam.getHonorScore() + 10);
            findAwayTeam.setTotalLoseRecord(findAwayTeam.getTotalLoseRecord() + 1);

//        homeTeam 패배한 경우
        } else if (homeTeamScore < awayTeamScore) {
            findHomeTeam.setHonorScore(findHomeTeam.getHonorScore() + 10);
            findHomeTeam.setTotalLoseRecord(findHomeTeam.getTotalLoseRecord() + 1);

            findAwayTeam.setHonorScore(findAwayTeam.getHonorScore() + 300);
            findAwayTeam.setTotalWinRecord(findAwayTeam.getTotalWinRecord() + 1);

//        무승부인 경우
        } else {
            findHomeTeam.setHonorScore(findHomeTeam.getHonorScore() + 100);
            findHomeTeam.setTotalDrawRecord(findHomeTeam.getTotalDrawRecord() + 1);
            findHomeTeam.setLeagueMatchPoints(findHomeTeam.getLeagueMatchPoints() + 1);

            findAwayTeam.setHonorScore(findAwayTeam.getHonorScore() + 100);
            findAwayTeam.setTotalDrawRecord(findAwayTeam.getTotalDrawRecord() + 1);

        }
        teamRepository.save(findHomeTeam);


        return teamRepository.save(findAwayTeam);
    }

    public Team updateForLeagueMatchEnd(
            Long homeTeamScore
            , Long awayTeamScore
            , Long homeTeamId
            , Long awayTeamId
    ) {
        Team findHomeTeam = findVerifiedTeam(homeTeamId);
        Team findAwayTeam = findVerifiedTeam(awayTeamId);
        if (homeTeamScore > awayTeamScore) {
            findHomeTeam.setHonorScore(findHomeTeam.getHonorScore() + 300);
            findHomeTeam.setTotalWinRecord(findHomeTeam.getTotalWinRecord() + 1);
            findHomeTeam.setLeagueMatchCount(findHomeTeam.getLeagueMatchCount() + 1);
            findHomeTeam.setLeagueMatchPoints(findHomeTeam.getLeagueMatchPoints() + 3);
            findHomeTeam.setLeagueWinRecord(findHomeTeam.getLeagueWinRecord() + 1);

            findAwayTeam.setHonorScore(findAwayTeam.getHonorScore() + 10);
            findAwayTeam.setTotalLoseRecord(findAwayTeam.getTotalLoseRecord() + 1);
            findAwayTeam.setLeagueLoseRecord(findAwayTeam.getLeagueLoseRecord() + 1);
            findAwayTeam.setLeagueMatchCount(findAwayTeam.getLeagueMatchCount() + 1);

//        homeTeam 패배한 경우
        } else if (homeTeamScore < awayTeamScore) {
            findHomeTeam.setHonorScore(findHomeTeam.getHonorScore() + 10);
            findHomeTeam.setTotalLoseRecord(findHomeTeam.getTotalLoseRecord() + 1);
            findHomeTeam.setLeagueLoseRecord(findHomeTeam.getLeagueLoseRecord() + 1);
            findHomeTeam.setLeagueMatchCount(findHomeTeam.getLeagueMatchCount() + 1);

            findAwayTeam.setHonorScore(findAwayTeam.getHonorScore() + 300);
            findAwayTeam.setTotalWinRecord(findAwayTeam.getTotalWinRecord() + 1);
            findAwayTeam.setLeagueMatchPoints(findAwayTeam.getLeagueMatchPoints() + 3);
            findAwayTeam.setLeagueWinRecord(findAwayTeam.getLeagueWinRecord() + 1);
            findAwayTeam.setLeagueMatchCount(findAwayTeam.getLeagueMatchCount() + 1);

//        무승부인 경우
        } else {
            findHomeTeam.setHonorScore(findHomeTeam.getHonorScore() + 100);
            findHomeTeam.setTotalDrawRecord(findHomeTeam.getTotalDrawRecord() + 1);
            findHomeTeam.setLeagueMatchPoints(findHomeTeam.getLeagueMatchPoints() + 1);
            findHomeTeam.setLeagueDrawRecord(findHomeTeam.getLeagueDrawRecord() + 1);
            findHomeTeam.setLeagueMatchCount(findHomeTeam.getLeagueMatchCount() + 1);

            findAwayTeam.setHonorScore(findAwayTeam.getHonorScore() + 100);
            findAwayTeam.setTotalDrawRecord(findAwayTeam.getTotalDrawRecord() + 1);
            findAwayTeam.setLeagueMatchPoints(findAwayTeam.getLeagueMatchPoints() + 1);
            findAwayTeam.setLeagueDrawRecord(findAwayTeam.getLeagueDrawRecord() + 1);
            findAwayTeam.setLeagueMatchCount(findAwayTeam.getLeagueMatchCount() + 1);

        }
        teamRepository.save(findHomeTeam);


        return teamRepository.save(findAwayTeam);
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

    public TeamMemberList findTeamMemberListByTeamId(long teamId) {
        Optional<TeamMemberList> optionalTeam = teamMemberListRepository.findByTeam_TeamId(teamId);
        TeamMemberList findTeamMemberList =
                optionalTeam.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.TEAM_MEMBER_LIST_NOT_FOUND));
        return findTeamMemberList;
    }

    public TeamMemberList findTeamMemberListByUserId(long teamId) {
        Optional<TeamMemberList> optionalTeam = teamMemberListRepository.findByUser_UserId(teamId);
        TeamMemberList findTeamMemberList =
                optionalTeam.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.TEAM_MEMBER_LIST_NOT_FOUND));
        return findTeamMemberList;
    }

    public void verifyTeamExistsByUserId(long userId) {
        Optional<Team> team = teamRepository.findByUser_UserId(userId);
        if (team.isPresent()) {
            throw new BusinessLogicException(Exceptions.TEAM_EXISTS);
        }
    }

    public void verifyTeamExistsByTeamName(String teamName) {
        Optional<Team> optionalTeam = teamRepository.findByTeamName(teamName);
        if (optionalTeam.isEmpty()) {
            throw new BusinessLogicException(Exceptions.TEAM_EXISTS);
        }
    }
}
