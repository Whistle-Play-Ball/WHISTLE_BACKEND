package company.board_project.match.match.repository;

import company.board_project.domain.match.leagueMatch.entity.LeagueMatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueMatchRepository extends JpaRepository<LeagueMatch, Long> {
}
