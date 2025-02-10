package company.board_project.match.match.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MatchPatchDto {
    private long matchId;
    private long userId;
    private long teamId;
    private long leagueListId;
    private int homeTeamHonorScore;
    private int homeTeamLeagueMatchPoints;
    private int homeTeamLeagueWinRecord;
    private int homeTeamLeagueDrawRecord;
    private int homeTeamLeagueLoseRecord;
    private int homeTeamTotalWinRecord;
    private int homeTeamTotalDrawRecord;
    private int homeTeamTotalLoseRecord;
    private String homeTeamName;
    private String homeTeamManagerName;
    private String homeTeamLevelType;
    private String homeTeamAgeType;
    private String homeTeamUniformType;
    private String matchType;
    private String sportsType;
    private String ageType;
    private String locationType;
    private String matchTime;
    private String matchDate;
    private String levelType;
    private String matchStatus;
    private String matchRules;

    public void updateId(Long id){
        this.matchId = id;
    }
}
