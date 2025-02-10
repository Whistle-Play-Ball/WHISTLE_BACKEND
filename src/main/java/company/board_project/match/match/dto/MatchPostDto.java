package company.board_project.match.match.dto;

import company.board_project.constant.MatchType;
import jakarta.validation.constraints.NotBlank;
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
    private int round;
}
