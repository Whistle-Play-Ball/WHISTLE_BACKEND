package company.board_project.apply.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LeagueMatchApplyPostDto {
    private long userId;
    private long teamId;
    private long leagueId;
    private long matchId;
    private long teamListId;
    private String applierName;
    private String teamName;
    private String levelType;
    private String ageType;
    private String matchType;
}
