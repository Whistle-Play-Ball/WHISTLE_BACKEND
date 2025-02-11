package company.board_project.match.match.dto;

import company.board_project.constant.LocationType;
import company.board_project.constant.MatchType;
import company.board_project.constant.SportsType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MatchPostDto {
    private MatchType matchType;
    // 홈팀 정보
    @NotBlank(message = "홈팀 이름을 입력 해야 합니다.")
    private String homeTeamName;

    // 어웨이팀 정보
    private String awayTeamName;

    // 리그 정보
    private String leagueName;
    private String TournamentName;

    // 필수 정보
    @NotNull(message = "지역 정보를 입력 해야 합니다.")
    private LocationType locationType;
    @NotNull(message = "SPORTS_TYPE을 입력 해야 합니다.")
    private SportsType sportsType;
    @NotBlank(message = "경기 시간을 입력 해야 합니다.")
    private String matchTime;
    @NotBlank(message = "경기장 주소를 입력 해야 합니다.")
    private String matchAddress;
    private String matchRules;

    private int round;
}
