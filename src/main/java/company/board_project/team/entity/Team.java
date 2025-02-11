package company.board_project.team.entity;

import company.board_project.audit.Auditable;
import company.board_project.constant.*;
import company.board_project.league.entity.League;
import company.board_project.match.match.entity.Match;
import company.board_project.apply.entity.Apply;
import company.board_project.user.entity.User;
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
@Table(name = "TEAM", indexes = {
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

//    @Column
//    private Integer leagueMatchCount;
//
//    @Column
//    private Integer leagueMatchPoints;
//
//    @Column
//    private Integer leagueWinRecord;
//
//    @Column
//    private Integer leagueDrawRecord;
//
//    @Column
//    private Integer leagueLoseRecord;
//
//    @Column
//    private Integer totalMatchCount;
//
//    @Column
//    private Integer totalWinRecord;
//
//    @Column
//    private Integer totalDrawRecord;
//
//    @Column
//    private Integer totalLoseRecord;
//
//    @Column
//    private Integer honorScore; // 명예 점수는 get 할때마다 쿼리로 계산해서 가져오기
//
//    @Column
//    private Integer teamGoals; // 총 득점 수는 쿼리로 계산
//
//    @Column
//    private Integer teamAssist; // 총 도움 수는 쿼리로 계산

    @Column(nullable = false)
    private String teamName;

    @Column
    private String introduction;

//    @Column(nullable = false)
//    private String managerName; // 매니저 이름은 변경될 수 있으므로 DB 저장하지 않고 쿼리로 호출

//    @Column
//    private String leagueName;

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

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", championCount=" + championCount +
                ", memberCount=" + memberCount +
                ", teamName='" + teamName + '\'' +
                ", introduction='" + introduction + '\'' +
                ", subManagerName='" + subManagerName + '\'' +
                ", ageType=" + ageType +
                ", sportsType=" + sportsType +
                ", levelType=" + levelType +
                ", locationType=" + locationType +
                ", frequency=" + frequency +
                ", uniformType=" + uniformType +
                ", formation=" + formation +
                ", leagues=" + leagues +
                ", applies=" + applies +
                ", matches=" + matches +
                ", user=" + user +
                '}';
    }
}
