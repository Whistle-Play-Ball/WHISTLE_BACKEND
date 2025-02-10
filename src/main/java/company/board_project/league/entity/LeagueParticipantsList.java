package company.board_project.league.entity;

import company.board_project.constant.*;
import company.board_project.apply.entity.Apply;
import company.board_project.team.entity.Team;
import company.board_project.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "LEAGUE_PARTICIPANTS_LISTS")
public class LeagueParticipantsList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leagueParticipantsListId;

    @Column
    private String teamName;

    @Column
    private String managerName;

    @Column
    private String leagueName;

    @Enumerated(EnumType.STRING)
    private Formation formation;

    @Enumerated(EnumType.STRING)
    private AgeType ageType;

    @Enumerated(EnumType.STRING)
    private LevelType levelType;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    @Enumerated(EnumType.STRING)
    private UniformType uniformType;

    @Column
    private Integer leagueMatchCount = 0;

    @Column
    private Integer leaguePoints = 0;

    @Column
    private Integer leagueWinRecord = 0;

    @Column
    private Integer leagueDrawRecord = 0;

    @Column
    private Integer leagueLoseRecord = 0;

    @Column
    private Integer teamGoals = 0;

    @Column
    private Integer teamAssist = 0;

    @Column
    private Integer cleanSheet = 0;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "LEAGUE_ID")
    private League league;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "APPLY_ID")
    private Apply apply;

    @Override
    public String toString() {
        return "LeagueParticipantsList{" +
                "leagueParticipantsListId=" + leagueParticipantsListId +
                ", teamName='" + teamName + '\'' +
                ", managerName='" + managerName + '\'' +
                ", leagueName='" + leagueName + '\'' +
                ", formation=" + formation +
                ", ageType=" + ageType +
                ", levelType=" + levelType +
                ", frequency=" + frequency +
                ", uniformType=" + uniformType +
                ", leagueMatchCount=" + leagueMatchCount +
                ", leaguePoints=" + leaguePoints +
                ", leagueWinRecord=" + leagueWinRecord +
                ", leagueDrawRecord=" + leagueDrawRecord +
                ", leagueLoseRecord=" + leagueLoseRecord +
                ", teamGoals=" + teamGoals +
                ", teamAssist=" + teamAssist +
                ", cleanSheet=" + cleanSheet +
                ", user=" + user +
                ", league=" + league +
                ", team=" + team +
                ", apply=" + apply +
                '}';
    }
}