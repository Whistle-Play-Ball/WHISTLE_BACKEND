package company.board_project.apply.entity;

import company.board_project.audit.Auditable;
import company.board_project.constant.AgeType;
import company.board_project.constant.AcceptType;
import company.board_project.constant.LevelType;
import company.board_project.constant.MatchType;
import company.board_project.league.entity.League;
import company.board_project.match.match.entity.Match;
import company.board_project.team.entity.Team;
import company.board_project.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "APPLIES")
public class Apply extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applyId;

    @Column
    private Long hostTeamId;

    @Column
    private Long hostLeagueId;

    @Column
    private String applierName;

    @Column
    private String teamName;

    @Column
    private String applyMessage;

    @Column
    int age;

    @Enumerated(EnumType.STRING)
    private LevelType levelType;

    @Enumerated(EnumType.STRING)
    private AgeType ageType;

    @Enumerated(EnumType.STRING)
    private MatchType matchType;

    @Enumerated(EnumType.STRING)
    private AcceptType acceptType = AcceptType.NONE;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "LEAGUE_ID")
    private League league;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "MATCH_ID")
    private Match match;

    @Override
    public String toString() {
        return "Apply{" +
                "applyId=" + applyId +
                ", hostTeamId=" + hostTeamId +
                ", hostLeagueId=" + hostLeagueId +
                ", applierName='" + applierName + '\'' +
                ", teamName='" + teamName + '\'' +
                ", applyMessage='" + applyMessage + '\'' +
                ", age=" + age +
                ", levelType=" + levelType +
                ", ageType=" + ageType +
                ", matchType=" + matchType +
                ", acceptType=" + acceptType +
                ", user=" + user +
                ", team=" + team +
                ", league=" + league +
                ", match=" + match +
                '}';
    }
}
