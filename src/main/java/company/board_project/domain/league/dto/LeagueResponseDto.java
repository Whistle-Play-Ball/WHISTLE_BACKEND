package company.board_project.domain.league.dto;

import company.board_project.domain.content.entity.Content;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class LeagueResponseDto {
    private Long leagueId;
    private Long userId;
    private Long teamId;
    private Long leagueEndCount;
    private Long memberCount;
    private Long matchCount;
    private Long teamCount;
    private Long honorScore;
    private Long winPoints;
    private String leagueName;
    private String managerName;
    private String managerTeamName;
    private String sportsType;
    private String ageType;
    private String locationType;
    private String period;
    private String levelType;
    private String leagueRules;
    private String frequency;
    private String seasonType;
    private List<Content> contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
