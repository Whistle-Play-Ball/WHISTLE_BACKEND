package company.board_project.team.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TeamPatchDto {
    private long teamId;
    private long userId;
    private int championCount;
    private int memberCount;
    private int leagueMatchPoints;
    private int leagueMatchCount;
    private int leagueWinRecord;
    private int leagueDrawRecord;
    private int leagueLoseRecord;
    private int totalMatchCount;
    private int totalWinRecord;
    private int totalDrawRecord;
    private int totalLoseRecord;
    private int honorScore;
    private int mostGoals;
    private int mostAssist;
    private int mostMom;

    private String formation;
    private String introduction;
    @NotBlank(message = "연령대를 입력 해야 합니다.")
    private String ageType;
    @NotBlank(message = "지역을 입력 해야 합니다.")
    private String locationType;
    @NotBlank(message = "운동 유형을 입력 해야 합니다.")
    private String sportsType;
    @NotBlank(message = "팀 실력을 입력 해야 합니다.")
    private String levelType;
    private String frequency;
    private String managerName;
    private String subManagerName;
    private String uniformType;
    private String leagueName;
}
