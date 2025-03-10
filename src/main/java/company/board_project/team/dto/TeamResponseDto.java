package company.board_project.team.dto;

import company.board_project.apply.entity.Apply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class TeamResponseDto {
    private Long teamId;
    private Long userId;
    private Integer championCount;
    private Integer memberCount;
    private Integer leagueMatchPoints;
    private Integer leagueMatchCount;
    private Integer leagueWinRecord;
    private Integer leagueDrawRecord;
    private Integer leagueLoseRecord;
    private Integer totalMatchCount;
    private Integer totalWinRecord;
    private Integer totalDrawRecord;
    private Integer totalLoseRecord;
    private Integer honorScore;
    private Integer mostGoals;
    private Integer mostAssist;

    private String formation;
    private String teamName;
    private String introduction;
    private String ageType;
    private String locationType;
    private String sportsType;
    private String levelType;
    private String managerName;
    private String subManagerName;
    private String frequency;
    private String uniformType;
    private String leagueName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private List<Apply> applies;

    @Override
    public String toString() {
        return "TeamResponseDto{" +
                "teamId=" + teamId +
                ", userId=" + userId +
                ", championCount=" + championCount +
                ", memberCount=" + memberCount +
                ", leagueMatchPoints=" + leagueMatchPoints +
                ", leagueMatchCount=" + leagueMatchCount +
                ", leagueWinRecord=" + leagueWinRecord +
                ", leagueDrawRecord=" + leagueDrawRecord +
                ", leagueLoseRecord=" + leagueLoseRecord +
                ", totalMatchCount=" + totalMatchCount +
                ", totalWinRecord=" + totalWinRecord +
                ", totalDrawRecord=" + totalDrawRecord +
                ", totalLoseRecord=" + totalLoseRecord +
                ", honorScore=" + honorScore +
                ", mostGoals=" + mostGoals +
                ", mostAssist=" + mostAssist +
                ", formation='" + formation + '\'' +
                ", teamName='" + teamName + '\'' +
                ", introduction='" + introduction + '\'' +
                ", ageType='" + ageType + '\'' +
                ", locationType='" + locationType + '\'' +
                ", sportsType='" + sportsType + '\'' +
                ", levelType='" + levelType + '\'' +
                ", managerName='" + managerName + '\'' +
                ", subManagerName='" + subManagerName + '\'' +
                ", frequency='" + frequency + '\'' +
                ", uniformType='" + uniformType + '\'' +
                ", leagueName='" + leagueName + '\'' +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", applies=" + applies +
                '}';
    }
}
