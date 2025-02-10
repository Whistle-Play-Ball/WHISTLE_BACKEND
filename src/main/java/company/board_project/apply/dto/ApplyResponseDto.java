package company.board_project.apply.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class ApplyResponseDto {
    private Long applyId;
    private Long userId;
    private Long teamId;
    private Long leagueId;
    private Long matchId;
    private Long teamListId;
    private String applierName;
    private String teamName;
    private String levelType;
    private String ageType;
    private String matchType;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
