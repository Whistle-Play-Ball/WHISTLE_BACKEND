package company.board_project.match.match.mapper;

import company.board_project.match.match.dto.*;
import company.board_project.match.match.entity.Match;
import company.board_project.team.entity.Team;
import company.board_project.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MatchMapper {
    Match matchPostDtoToMatch(MatchPostDto requestBody);

    Match matchPatchDtoToMatch(MatchPatchDto requestBody);
    CreateNormalMatchResponseDto matchToCreateNormalMatchResponseDto(Match requestBody);
    CreateLeagueMatchResponseDto matchToCreateLeagueMatchResponseDto(Match requestBody);
    CreateTournamentMatchResponseDto matchToCreateTournamentMatchResponseDto(Match requestBody);
    List<Match> findByTeamIdAndMatchStatus(Long teamId, String matchStatus);

    default MatchResponseDto matchToMatchResponse(Match match){
        User user = match.getUser();
        Team team = match.getTeam();

        return MatchResponseDto.builder()
                .matchId(match.getMatchId())
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .homeTeamHonorScore(team.getHonorScore())
                .homeTeamName(team.getTeamName())
                .homeTeamManagerName(team.getManagerName())
                .homeTeamTotalWinRecord(team.getTotalWinRecord())
                .homeTeamTotalDrawRecord(team.getTotalDrawRecord())
                .homeTeamTotalLoseRecord(team.getTotalLoseRecord())
                .homeTeamLevelType(String.valueOf(team.getLevelType()))
                .homeTeamAgeType(String.valueOf(team.getAgeType()))
                .homeTeamUniformType(String.valueOf(team.getUniformType()))
                .sportsType(String.valueOf(match.getSportsType()))
                .locationType(String.valueOf(match.getLocationType()))
                .matchTime(match.getMatchTime())
                .matchStatus(String.valueOf(match.getMatchStatus()))
                .matchType(String.valueOf(match.getMatchType()))
                .createdAt(match.getCreatedAt())
                .modifiedAt(match.getModifiedAt())
                .build();
    }

    default MatchListDto matchListDtoToMatchResponse(List<Match> matches){

        return MatchListDto.builder()
                .matchResponseDto(matchesToMatchesResponse(matches))
                .build();
    }

    List<MatchResponseDto> matchesToMatchesResponse(List<Match> matches);
}
