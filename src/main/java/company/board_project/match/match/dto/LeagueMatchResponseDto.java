package company.board_project.match.match.dto;

import company.board_project.constant.*;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LeagueMatchResponseDto {
    private Long homeTeamId;
    private String homeTeamName;
    private String homeTeamManagerName;
    private Integer homeTeamHonorScore;
    private Integer homeTeamLeagueWinRecord;
    private Integer homeTeamLeagueDrawRecord;
    private Integer homeTeamLeagueLoseRecord;
    private LevelType homeTeamLevelType;
    private AgeType homeTeamAgeType;
    private UniformType homeTeamUniformType;
    private MatchResultStatus homeTeamMatchResultStatus = MatchResultStatus.NONE;

    private Long awayTeamId;
    private String awayTeamName;
    private String awayTeamManagerName;
    private Integer awayTeamHonorScore;
    private Integer awayTeamLeagueWinRecord;
    private Integer awayTeamLeagueDrawRecord;
    private Integer awayTeamLeagueLoseRecord;
    private LevelType awayTeamLevelType;
    private AgeType awayTeamAgeType;
    private UniformType awayTeamUniformType;
    private MatchResultStatus awayTeamMatchResultStatus = MatchResultStatus.NONE;

    private Long leagueId;
    private Long leagueMatchId;
    private LocationType locationType;
    private MatchType matchType;
    private SportsType sportsType;
    private Integer round;
    private String matchAddress;
    private String matchTime;
    private String matchRules;
    private String leagueName;
    private MatchStatus matchStatus = MatchStatus.BEFORE;
}
