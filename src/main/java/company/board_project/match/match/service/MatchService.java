package company.board_project.match.match.service;

import company.board_project.constant.MatchType;
import company.board_project.constant.TeamMemberRole;
import company.board_project.league.entity.League;
import company.board_project.league.service.LeagueService;
import company.board_project.match.match.dto.MatchPostDto;
import company.board_project.match.match.entity.Match;
import company.board_project.match.match.repository.MatchRepository;
import company.board_project.team.entity.TeamMemberInfo;
import company.board_project.exception.BusinessLogicException;
import company.board_project.exception.Exceptions;
import company.board_project.team.entity.Team;
import company.board_project.team.service.TeamService;
import company.board_project.user.entity.User;
import company.board_project.user.repository.UserRepository;
import company.board_project.user.service.UserService;
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
@RequiredArgsConstructor
@Log4j2
public class MatchService {
    private final MatchRepository matchRepository;
    private final UserService userService;
    private final TeamService teamService;
    private final LeagueService leagueService;
    private final UserRepository userRepository;

    public Match createMatch(String email, MatchPostDto matchPostDto, Match match) {
        // 유저가 가입된 유저인지 확인
        User user = userService.findUserByEmail(email);

        // 유저가 팀이 있는지 확인
        Team teamByUser = teamService.findTeamByUserId(user.getUserId());

        // 유저가 경기를 생성할 권한이 있는지 확인 (매니저 혹은 부매니저)
        TeamMemberInfo teamMemberInfoByUserId = teamService.findTeamMemberListByUserId(user.getUserId());
        if (!(teamMemberInfoByUserId.getTeamMemberRole().equals(TeamMemberRole.MANAGER) || teamMemberInfoByUserId.getTeamMemberRole().equals(TeamMemberRole.SUB_MANAGER))) {
            throw new BusinessLogicException(Exceptions.USER_HAS_NO_RIGHT);
        }

        // postDto에 있는 homeTeamName 정보가 존재하는지 확인
        teamService.findTeamByTeamName(matchPostDto.getHomeTeamName());

        // 유저의 팀정보와 teamByPostDto의 팀정보가 다르면 예외 발생
        if (!matchPostDto.getHomeTeamName().equals(teamByUser.getTeamName())) {
            throw new BusinessLogicException(Exceptions.TEAM_INFO_DIFFERENCE);
        }

        // 검증 이상 없다면 매치 정보 등록
        match.setHomeTeamName(teamByUser.getTeamName());
        match.setHomeTeamUniformType(teamByUser.getUniformType());
        match.setMatchType(MatchType.NORMAL);

        matchRepository.save(match);

        return match;
    }

    public Match createLeagueMatch(String email, MatchPostDto matchPostDto, Match match) {
        log.info("CREATE LEAGUE_MATCH START");
        // 유저가 가입된 유저인지 확인
        User user = userService.findUserByEmail(email);

        // 유저가 속한 팀이 해당 리그 소속인지 확인
        Team teamByUser = teamService.findTeamByUserId(user.getUserId());
        League league = leagueService.findVerifiedExistsLeagueByTeamId(teamByUser.getTeamId());
        if (matchPostDto.getLeagueName() == null) {
            throw new BusinessLogicException(Exceptions.TEAM_PARAMETER_EXCEPTION);

        } else {
            if (!matchPostDto.getLeagueName().equals(league.getLeagueName())) {
                throw new BusinessLogicException(Exceptions.LEAGUE_INFO_DIFFERENCE);
            }
        }

        // 유저가 리그 경기를 생성할 수 있는 권한이 있는지 확인하는 로직 (리그 권한이 따로 필요한지 확인)
        TeamMemberInfo teamMemberInfoByUserId = teamService.findTeamMemberListByUserId(user.getUserId());
        if (!(teamMemberInfoByUserId.getTeamMemberRole().equals(TeamMemberRole.MANAGER) || teamMemberInfoByUserId.getTeamMemberRole().equals(TeamMemberRole.SUB_MANAGER))) {
            throw new BusinessLogicException(Exceptions.USER_HAS_NO_RIGHT);
        }

        // 홈팀 이름 파라미터 검증
        Team homeTeam;
        if (matchPostDto.getHomeTeamName() == null) {
            throw new BusinessLogicException(Exceptions.TEAM_PARAMETER_EXCEPTION);
        } else {
            homeTeam = teamService.findTeamByTeamName(matchPostDto.getHomeTeamName());
        }

        // 어웨이팀 이름 파라미터 검증
        Team awayTeam;
        if (matchPostDto.getAwayTeamName() == null) {
            throw new BusinessLogicException(Exceptions.TEAM_PARAMETER_EXCEPTION);
        } else {
            awayTeam = teamService.findTeamByTeamName(matchPostDto.getAwayTeamName());
        }

        // 홈팀 또는 어웨이팀이 가입한 리그가 없으면 Exception 발생
        League homeTeamLeague = leagueService.findVerifiedExistsLeagueByTeamId(homeTeam.getTeamId());
        League awayTeamLeague = leagueService.findVerifiedExistsLeagueByTeamId(awayTeam.getTeamId());

        // 홈팀과 어웨이팀이 같은 리그인지 확인
        if (!homeTeamLeague.getLeagueId().equals(awayTeamLeague.getLeagueId())) {
            throw new BusinessLogicException(Exceptions.BOTH_TEAMS_ARE_DIFFERENT_LEAGUE);
        }

        try {
            match.setHomeTeam(homeTeam);
            match.setHomeTeamName(homeTeam.getTeamName());
            match.setHomeTeamUniformType(homeTeam.getUniformType());

            match.setAwayTeam(awayTeam);
            match.setAwayTeamName(awayTeam.getTeamName());
            match.setAwayTeamUniformType(awayTeam.getUniformType());

            match.setMatchType(MatchType.LEAGUE);

            matchRepository.save(match);
            log.info("CREATE LEAGUE_MATCH END");
        } catch (Exception e) {
            throw new BusinessLogicException(Exceptions.QUERY_ERROR);
        }
        return match;
    }

    public Match createTournamentMatch(String email, MatchPostDto matchPostDto, Match match) {
        // 유저가 가입된 유저인지 확인
        User user = userService.findUserByEmail(email);

        // 유저가 속한 대회가 해당 대회 소속인지 확인
        Team teamByUser = teamService.findTeamByUserId(user.getUserId());

        // 유저가 대회 생성 권한이 있는지 확인



        Team homeTeam = teamService.findTeamByTeamName(matchPostDto.getHomeTeamName());
        Team awayTeam = teamService.findTeamByTeamName(matchPostDto.getAwayTeamName());

        // 홈팀과 어웨이팀이 다른 대회 소속인 경우 예외 처리

        match.setMatchType(match.getMatchType());
        match.setUser(user);

        match.setHomeTeam(homeTeam);
        match.setHomeTeamName(homeTeam.getTeamName());
        match.setHomeTeamUniformType(homeTeam.getUniformType());

        match.setAwayTeam(awayTeam);
        match.setAwayTeamName(awayTeam.getTeamName());
        match.setAwayTeamUniformType(awayTeam.getUniformType());

        matchRepository.save(match);

        return match;
    }

    public Match updateMatch(Match match) {

        Match findMatch = findVerifiedMatch(match.getMatchId());

        Optional.ofNullable(match.getHomeTeamName())
                .ifPresent(findMatch::setHomeTeamName);

        Optional.ofNullable(match.getHomeTeamUniformType())
                .ifPresent(findMatch::setHomeTeamUniformType);

        Optional.ofNullable(match.getMatchType())
                .ifPresent(findMatch::setMatchType);

        Optional.ofNullable(match.getMatchAt())
                .ifPresent(findMatch::setMatchAt);

        Optional.ofNullable(match.getMatchStatus())
                .ifPresent(findMatch::setMatchStatus);

        Optional.ofNullable(match.getHomeTeamMatchResultStatus())
                .ifPresent(findMatch::setHomeTeamMatchResultStatus);

        Optional.ofNullable(match.getAwayTeamMatchResultStatus())
                .ifPresent(findMatch::setAwayTeamMatchResultStatus);


        return matchRepository.save(findMatch);
    }


    public Match findMatch(Long matchId) {
        return findVerifiedMatch(matchId);
    }

    public Page<Match> findMatches(int page, int size) {
        return matchRepository.findAll(PageRequest.of(page, size,
                Sort.by("matchId").descending()));
    }

    public List<Match> findAllSearch(String keyword) {
        return matchRepository.findAllSearch(keyword);
    }

    public List<Match> findAllSearchByUserName(String name) {
        return matchRepository.findAllSearchByUserName(name);
    }

    public List<Match> findMatchesNewest() {
        return matchRepository.findMatchesNewest();
    }

    public List<Match> findMatchesLatest() {
        return matchRepository.findMatchesLatest();
    }

    public void deleteMatch(Long matchId) {
        Match findMatch = findVerifiedMatch(matchId);

        matchRepository.delete(findMatch);
    }

    public User findVerifiedUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User findUser =
                optionalUser.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.USER_NOT_FOUND));
        return findUser;
    }

    public Match findVerifiedMatch(Long matchId) {
        Optional<Match> optionalMatch = matchRepository.findById(matchId);

        Match findMatch =
                optionalMatch.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.CONTENT_NOT_FOUND));

        return findMatch;
    }

    public Match findVerifiedExistsLeagueByTeamId(long teamId) {
        Match match = matchRepository.findByVerifiedTeamId(teamId);
        if (match == null) {
            try {
            } catch (NoSuchElementException ex) {
                throw new BusinessLogicException(Exceptions.MATCH_EXISTS);
            }
            return match;
        }
        throw new BusinessLogicException(Exceptions.MATCH_EXISTS);
    }
}