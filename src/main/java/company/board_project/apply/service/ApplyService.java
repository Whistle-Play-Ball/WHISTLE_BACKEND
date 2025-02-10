package company.board_project.apply.service;

import company.board_project.apply.entity.Apply;
import company.board_project.apply.repository.ApplyRepository;
import company.board_project.exception.BusinessLogicException;
import company.board_project.exception.Exceptions;
import company.board_project.league.entity.League;
import company.board_project.league.service.LeagueService;
import company.board_project.match.match.entity.Match;
import company.board_project.match.match.service.MatchService;
import company.board_project.team.entity.Team;
import company.board_project.team.service.TeamService;
import company.board_project.user.entity.User;
import company.board_project.user.repository.UserRepository;
import company.board_project.user.service.UserService;
import company.board_project.util.CommonUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/*
 * ApplyService
 */
@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class ApplyService {
    private final ApplyRepository applyRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final TeamService teamService;
    private final MatchService matchService;
    private final LeagueService leagueService;
    private final CommonUtils commonUtils;

//    /*
//     * Apply 생성
//     * user, team이 존재하는지 확인 후 team, user가 존재하면 applyRepository에 저장
//     */
//    public Apply createApply(Apply apply, Long userId, Long teamId) {
//        User user = userService.findUser(userId);
//        Team team = teamService.findTeam(teamId);
//
//        apply.setTeam(team);
//        apply.setUser(user);
//
//        applyRepository.save(apply);
//
//        return apply;
//    }

    /*
     * TeamApply 생성
     */
    public Apply createTeamApply(Apply apply, String email, Long teamId) {
        int age =0;
        log.info("CREATE TEAM APPLY START[{}]", apply);

        log.info("apply[{}]", apply);
        User user = userService.findUserByEmail(email);
        Team team = teamService.findTeam(teamId);

        apply.setApplierName(user.getName());
        apply.setTeamName(team.getTeamName());
        apply.setTeam(team);
        apply.setUser(user);
        apply.setAgeType(commonUtils.ageChecker(apply.getAge()));

        applyRepository.save(apply);
        return apply;
    }

    /*
     * MatchApply 생성
     * user, team, match가 존재하는지 확인 후 team, user, match가 존재하면 applyRepository에 저장
     */
    public Apply createMatchApply(Apply apply, String email, Long matchId, Long teamId) {
        log.info("CREATE MATCH APPLY START[{}]", apply);

        User user = userService.findUserByEmail(email);
        Match match = matchService.findMatch(matchId);
        Team team = teamService.findTeam(teamId);

        apply.setUser(user);
        apply.setMatch(match);
        apply.setTeam(team);

        apply.setApplierName(team.getManagerName());
        apply.setTeamName(team.getTeamName());
        apply.setLevelType(team.getLevelType());
        apply.setAgeType(team.getAgeType());

        applyRepository.save(apply);

        return apply;
    }

    /*
     * LeagueApply 생성
     * user, team, league가 존재하는지 확인 후 team, user, league가 존재하면 applyRepository에 저장
     */
    public Apply createLeagueApply(Apply apply, String email, Long leagueId, Long teamId) {
        log.info("CREATE LEAGUE APPLY START[{}]", apply);

        User user = userService.findUserByEmail(email);
        League league = leagueService.findLeague(leagueId);
        Team team = teamService.findTeam(teamId);

        apply.setLeague(league);
        apply.setUser(user);
        apply.setTeam(team);

        applyRepository.save(apply);

        return apply;
    }

    public Apply findApply(Long applyId) {
        return findVerifiedApply(applyId);
    }

    public List<Apply> findAllByTeamId(Long teamId){
        return applyRepository.findAllByTeamId(teamId);
    }

    public List<Apply> findAllByMatchId(Long matchId){
        return applyRepository.findAllByMatchId(matchId);
    }

    public List<Apply> findAllByLeagueId(Long leagueId){
        return applyRepository.findAllByLeagueId(leagueId);
    }

    public void deleteApply(Long applyId) {
        Apply findApply = findVerifiedApply(applyId);

        applyRepository.delete(findApply);
    }


    /*public User findVerifiedUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User findUser =
                optionalUser.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.USER_NOT_FOUND));
        return findUser;
    }*/

    /*
     * apply 검증 로직
     * repository에 해당apply가 없는 경우 exception을 리턴한다.
     */
    public Apply findVerifiedApply(Long applyId) {
        Optional<Apply> optionalApply = applyRepository.findById(applyId);

        Apply findApply =
                optionalApply.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.APPLY_NOT_FOUND));

        return findApply;
    }
}
