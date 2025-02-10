package company.board_project.match.match.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class MatchResponseDto {
    private Long matchId;
    private Long userId;
    private Long teamId;
    private Integer homeTeamHonorScore;
    private String homeTeamName;
    private String homeTeamManagerName;
    private Integer homeTeamTotalWinRecord;
    private Integer homeTeamTotalDrawRecord;
    private Integer homeTeamTotalLoseRecord;
    private String homeTeamLevelType;
    private String homeTeamAgeType;
    private String homeTeamUniformType;
    private String matchType;
    private String sportsType;
    private String locationType;
    private String matchTime;
    private String matchDate;
    private String matchStatus;
    private String matchRules;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
