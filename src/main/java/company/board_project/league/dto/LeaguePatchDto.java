package company.board_project.league.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LeaguePatchDto {
    private long leagueId;
    private long userId;
    private long teamId;
    private int matchCount;
    private int memberCount;
    private int winPoints;
    @NotBlank(message = "리그의 이름을 입력 해야 합니다.")
    private String leagueName;
    @NotBlank(message = "운동의 유형을 입력 해야 합니다.")
    private String sportsType;
    private int teamCount;
    @NotBlank(message = "연령대를 입력 해야 합니다.")
    private String ageType;
    @NotBlank(message = "지역을 입력 해야 합니다.")
    private String locationType;
    @NotBlank(message = "리그 경기 기간을 입력 해야 합니다.")
    private String period;
    @NotBlank(message = "리그의 난이도를 입력 해야 합니다.")
    private String levelType;
    private String leagueRules;
    @NotBlank(message = "리그의 경기 빈도를 입력 해야 합니다.")
    private String frequency;
    private String seasonType;

    public void updateId(Long id){
        this.leagueId = id;
    }
}
