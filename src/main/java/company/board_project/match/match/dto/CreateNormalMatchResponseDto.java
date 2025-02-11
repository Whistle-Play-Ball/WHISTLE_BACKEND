package company.board_project.match.match.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateNormalMatchResponseDto {
    private Long matchId;
    private String homeTeamName;
    private String homeTeamUniformType;
    private String matchType;
    private String sportsType;
    private String locationType;
    private String matchTime;
    private String matchStatus;
    private String matchAddress;
    private String matchRules;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
