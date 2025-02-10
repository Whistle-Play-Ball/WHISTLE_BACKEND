package company.board_project.match.match.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NormalMatchResponseDto {
    private Long matchId;
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
