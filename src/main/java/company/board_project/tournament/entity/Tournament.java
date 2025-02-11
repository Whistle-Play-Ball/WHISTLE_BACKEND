package company.board_project.tournament.entity;

import company.board_project.audit.Auditable;
import company.board_project.constant.TournamentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TOURNAMENT", indexes = {
        @Index(name = "idx_tournament_name", columnList = "tournament_name")
})
public class Tournament extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tournamentId;

    @Column
    private Integer round;

    @Column
    private Integer memberCount;

    @Column
    private String TournamentIntroduction;

    @Column
    private String matchAt;

    @Column
    private String tournamentManager;

    @Column
    private TournamentStatus tournamentStatus = TournamentStatus.IN_PROGRESS;
}
