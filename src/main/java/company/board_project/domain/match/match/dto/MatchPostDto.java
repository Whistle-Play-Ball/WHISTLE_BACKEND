package company.board_project.domain.match.match.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MatchPostDto {
    private Long teamId;
    @NotBlank(message = "지역을 입력 해야 합니다.")
    private String locationType;
    @NotBlank(message = "경기 시간을 입력 해야 합니다.")
    private String matchTime;
    @NotBlank(message = "경기 날짜를 입력 해야 합니다.")
    private String matchDate;
    private String matchRules;
    @NotBlank(message = "경기 타입을 입력 해야 합니다.")
    private String matchType;
}
