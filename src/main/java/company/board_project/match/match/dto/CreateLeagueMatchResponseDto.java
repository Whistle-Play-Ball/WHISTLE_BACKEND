package company.board_project.match.match.dto;

import company.board_project.constant.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateLeagueMatchResponseDto {
    private Long matchId;

    private String homeTeamName;
    private UniformType homeTeamUniformType;
    private MatchResultStatus homeTeamMatchResultStatus = MatchResultStatus.NONE;

    private String awayTeamName;
    private UniformType awayTeamUniformType;
    private MatchResultStatus awayTeamMatchResultStatus = MatchResultStatus.NONE;

    private Long leagueId;
    private LocationType locationType;
    private MatchType matchType;
    private SportsType sportsType;
    private String matchAddress;
    private String matchAt;
    private String matchRules;
    private String matchName;
    private Integer matchRound;
    private MatchStatus matchStatus = MatchStatus.BEFORE;
}
