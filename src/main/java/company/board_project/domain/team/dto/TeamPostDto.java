package company.board_project.domain.team.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TeamPostDto {
    @NotBlank(message = "팀 이름을 입력 해야 합니다.")
    private String teamName;
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
    private String formation;

    private int championCount = 0;
    private int memberCount = 1;
    private int leagueMatchPoints = 0;
    private int leagueMatchCount = 0;
    private int leagueWinRecord = 0;
    private int leagueDrawRecord = 0;
    private int leagueLoseRecord = 0;
    private int totalMatchCount = 0;
    private int totalWinRecord = 0;
    private int totalDrawRecord = 0;
    private int totalLoseRecord = 0;
    private int honorScore = 0;
    private int mostGoals = 0;
    private int mostAssist = 0;
    private int mostMom = 0;

}
