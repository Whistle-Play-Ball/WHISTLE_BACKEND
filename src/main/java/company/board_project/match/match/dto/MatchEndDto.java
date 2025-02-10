package company.board_project.match.match.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MatchEndDto {
    private long matchId;
    private long matchListId;
    private long homeTeamUserId;
    private long awayTeamUserId;
    private long homeTeamId;
    private long awayTeamId;
    private int homeTeamScore;
    private int awayTeamScore;
    private String matchStatus;

}
