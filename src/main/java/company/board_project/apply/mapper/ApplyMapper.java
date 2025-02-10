package company.board_project.apply.mapper;

import company.board_project.apply.dto.*;
import company.board_project.domain.apply.dto.*;
import company.board_project.apply.entity.Apply;
import company.board_project.league.entity.League;
import company.board_project.match.match.entity.Match;
import company.board_project.team.entity.Team;
import company.board_project.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ApplyMapper {

//    Apply applyPostDtoToApply(ApplyPostDto requestBody);

    Apply teamApplyPostDtoToTeamApply(TeamApplyPostDto requestBody);

    Apply matchApplyPostDtoToMatchApply(MatchApplyPostDto requestBody);

    Apply leagueApplyPostDtoToLeagueApply(LeagueApplyPostDto requestBody);

    default ApplyResponseDto applyToApplyResponse(Apply apply){

        User user = apply.getUser();
        Team team = apply.getTeam();

        return ApplyResponseDto.builder()
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .applyId(apply.getApplyId())
                .teamName(apply.getTeamName())
                .createdAt(apply.getCreatedAt())
                .modifiedAt(apply.getModifiedAt())
                .build();
    }

    TeamApplyResponseDto applyToTeamApplyResponse(Apply apply);

    default LeagueApplyResponseDto applyToLeagueApplyResponse(Apply apply){

        User user = apply.getUser();
        Team team = apply.getTeam();
        League league = apply.getLeague();

        return LeagueApplyResponseDto.builder()
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .leagueId(league.getLeagueId())
                .applyId(apply.getApplyId())
                .teamName(apply.getTeamName())
                .applierName(apply.getApplierName())
                .levelType(String.valueOf(apply.getLevelType()))
                .ageType(String.valueOf(apply.getAgeType()))
                .matchType(String.valueOf(apply.getMatchType()))
                .createdAt(apply.getCreatedAt())
                .modifiedAt(apply.getModifiedAt())
                .build();
    }

    default MatchApplyResponseDto applyToMatchApplyResponse(Apply apply){

        User user = apply.getUser();
        Team team = apply.getTeam();
        Match match = apply.getMatch();

        return MatchApplyResponseDto.builder()
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .matchId(match.getMatchId())
                .applyId(apply.getApplyId())
                .applierName(apply.getApplierName())
                .levelType(String.valueOf(apply.getLevelType()))
                .ageType(String.valueOf(apply.getAgeType()))
                .matchType(String.valueOf(apply.getMatchType()))
                .teamName(apply.getTeamName())
                .createdAt(apply.getCreatedAt())
                .modifiedAt(apply.getModifiedAt())
                .build();
    }

    default ApplyListDto applyListDtoToApplyResponse(List<Apply> applies){

        return ApplyListDto.builder()
                .applyResponseDtoList(appliesToApplyResponse(applies))
                .build();
    }

    default List<ApplyResponseDto> appliesToApplyResponse(List<Apply> applies){
        return applies.stream()
                .map(apply -> ApplyResponseDto.builder()
                        .applyId(apply.getApplyId())
                        .teamName(apply.getTeamName())
                        .matchType(apply.getApplierName())
                        .matchType(String.valueOf(apply.getMatchType()))
                        .levelType(String.valueOf(apply.getLevelType()))
                        .ageType(String.valueOf(apply.getAgeType()))
                        .createdAt(apply.getCreatedAt())
                        .modifiedAt(apply.getModifiedAt())
                        .build())
                .collect(Collectors.toList());
    }
}

