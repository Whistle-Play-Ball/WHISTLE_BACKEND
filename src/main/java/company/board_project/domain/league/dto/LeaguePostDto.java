package company.board_project.domain.league.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LeaguePostDto {
    private long userId;
    private long teamId;
    private int memberCount;
    private int matchCount;
    private int teamCount = 1;
    private int leagueEndCount = 0;
    private String teamName;
    private int teamGoals = 0;
    private int teamAssist = 0;
    private int cleanSheet = 0;
    private String managerName;
    private String subManagerName;
    private String managerTeamName;
    @NotBlank(message = "리그의 이름을 입력 해야 합니다.")
    private String leagueName;
    @NotBlank(message = "운동의 유형을 입력 해야 합니다.")
    private String sportsType;
    @NotBlank(message = "연령대를 입력 해야 합니다.")
    private String ageType;
    @NotBlank(message = "지역을 입력 해야 합니다.")
    private String locationType;
    @NotBlank(message = "리그 경기 기간을 입력 해야 합니다.")
    private String period;
    @NotBlank(message = "리그의 실력을 입력 해야 합니다.")
    private String levelType;
    private String leagueRules;
    @NotBlank(message = "리그의 선호 경기 빈도를 입력 해야 합니다.")
    private String frequency;
    private String seasonType="TEAM_RECRUIT";
    private String title;
    private String content;

}
