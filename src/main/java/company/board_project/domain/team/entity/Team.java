package company.board_project.domain.team.entity;

import company.board_project.audit.Auditable;
import company.board_project.constant.*;
import company.board_project.domain.league.entity.League;
import company.board_project.domain.match.match.entity.Match;
import company.board_project.domain.apply.entity.Apply;
import company.board_project.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TEAMS", indexes = {
        @Index(name = "idx_team_name", columnList = "team_name")
})
public class Team extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    @Column
    private Integer championCount;

    @Column
    private Integer memberCount;

    @Column
    private Integer leagueMatchCount;

    @Column
    private Integer leagueMatchPoints;

    @Column
    private Integer leagueWinRecord;

    @Column
    private Integer leagueDrawRecord;

    @Column
    private Integer leagueLoseRecord;

    @Column
    private Integer totalMatchCount;

    @Column
    private Integer totalWinRecord;

    @Column
    private Integer totalDrawRecord;

    @Column
    private Integer totalLoseRecord;

    @Column
    private Integer honorScore;

    @Column
    private Integer teamGoals;

    @Column
    private Integer teamAssist;

    @Column(nullable = false)
    private String teamName;

    @Column
    private String introduction;

    @Column(nullable = false)
    private String managerName;

    @Column
    private String leagueName;

    @Column
    private String subManagerName;

    @Enumerated(EnumType.STRING)
    private AgeType ageType;

    @Enumerated(EnumType.STRING)
    private SportsType sportsType;

    @Enumerated(EnumType.STRING)
    private LevelType levelType;

    @Enumerated(EnumType.STRING)
    private LocationType locationType;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    @Enumerated(EnumType.STRING)
    private UniformType uniformType;

    @Enumerated(EnumType.STRING)
    private Formation formation;

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    private List<League> leagues = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    private List<Apply> applies = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    private List<Match> matches = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "USER_ID")
    private User user;

}
