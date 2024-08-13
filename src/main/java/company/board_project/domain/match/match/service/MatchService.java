package company.board_project.domain.match.match.service;

import company.board_project.domain.match.match.entity.Match;
import company.board_project.domain.match.match.repository.MatchRepository;
import company.board_project.domain.team.repository.TeamRepository;
import company.board_project.exception.BusinessLogicException;
import company.board_project.exception.Exceptions;
import company.board_project.domain.team.entity.Team;
import company.board_project.domain.team.service.TeamService;
import company.board_project.domain.user.entity.User;
import company.board_project.domain.user.repository.UserRepository;
import company.board_project.domain.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;
    private final UserService userService;
    private final TeamService teamService;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    public Match createMatch(Match match, String email, Long teamId) {
        User user = userService.findUserByEmail(email);
        Team team = teamService.findTeam(teamId);

        match.setUser(user);
        match.setTeam(team);

        match.setHomeTeamHonorScore(team.getHonorScore());
        match.setHomeTeamName(team.getTeamName());
        match.setHomeTeamManagerName(team.getManagerName());
        match.setHomeTeamTotalWinRecord(team.getTotalWinRecord());
        match.setHomeTeamTotalDrawRecord(team.getTotalDrawRecord());
        match.setHomeTeamTotalLoseRecord(team.getTotalLoseRecord());
        match.setHomeTeamLevelType(team.getLevelType());
        match.setHomeTeamAgeType(team.getAgeType());
        match.setHomeTeamUniformType(team.getUniformType());
        match.setMatchType(match.getMatchType());

        userRepository.save(user);
        matchRepository.save(match);

        return match;
    }

    public Match createTournamentMatch(Match match, String email, Long teamId) {
        User user = userService.findUserByEmail(email);
        Team team = teamService.findTeam(teamId);

        findVerifiedExistsLeagueByTeamId(teamId);

        match.setUser(user);
        match.setTeam(team);

        match.setHomeTeamHonorScore(team.getHonorScore());
        match.setHomeTeamName(team.getTeamName());
        match.setHomeTeamManagerName(team.getManagerName());
        match.setHomeTeamTotalWinRecord(team.getTotalWinRecord());
        match.setHomeTeamTotalDrawRecord(team.getTotalDrawRecord());
        match.setHomeTeamTotalLoseRecord(team.getTotalLoseRecord());
        match.setHomeTeamLevelType(team.getLevelType());
        match.setHomeTeamAgeType(team.getAgeType());
        match.setHomeTeamUniformType(team.getUniformType());
        match.setMatchType(match.getMatchType());

        userRepository.save(user);
        matchRepository.save(match);

        return match;
    }

    public Match updateMatch(Match match) {

        Match findMatch = findVerifiedMatch(match.getMatchId());

        Optional.ofNullable(match.getHomeTeamHonorScore())
                .ifPresent(findMatch::setHomeTeamHonorScore);

        Optional.ofNullable(match.getHomeTeamName())
                .ifPresent(findMatch::setHomeTeamName);

        Optional.ofNullable(match.getHomeTeamManagerName())
                .ifPresent(findMatch::setHomeTeamManagerName);

        Optional.ofNullable(match.getHomeTeamTotalWinRecord())
                .ifPresent(findMatch::setHomeTeamTotalWinRecord);

        Optional.ofNullable(match.getHomeTeamTotalDrawRecord())
                .ifPresent(findMatch::setHomeTeamTotalDrawRecord);

        Optional.ofNullable(match.getHomeTeamTotalLoseRecord())
                .ifPresent(findMatch::setHomeTeamTotalLoseRecord);

        Optional.ofNullable(match.getHomeTeamLevelType())
                .ifPresent(findMatch::setHomeTeamLevelType);

        Optional.ofNullable(match.getHomeTeamAgeType())
                .ifPresent(findMatch::setHomeTeamAgeType);

        Optional.ofNullable(match.getHomeTeamUniformType())
                .ifPresent(findMatch::setHomeTeamUniformType);

        Optional.ofNullable(match.getMatchType())
                .ifPresent(findMatch::setMatchType);

        Optional.ofNullable(match.getMatchTime())
                .ifPresent(findMatch::setMatchTime);

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

    public List<Match> findAllSearch(String keyword){
        return matchRepository.findAllSearch(keyword);
    }

    public List<Match> findAllSearchByUserName(String name){
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
        if(match ==null) {
            try {
            } catch (NoSuchElementException ex) {
                throw new BusinessLogicException(Exceptions.MATCH_EXISTS);
            }
            return match;
        }
        throw new BusinessLogicException(Exceptions.MATCH_EXISTS);
    }
}