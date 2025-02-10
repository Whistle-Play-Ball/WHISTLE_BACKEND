package company.board_project.match.match.repository;

import company.board_project.match.match.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueMatchRepository extends JpaRepository<Match, Long> {
}
