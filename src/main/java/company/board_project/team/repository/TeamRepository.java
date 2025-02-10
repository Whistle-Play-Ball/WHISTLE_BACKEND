package company.board_project.team.repository;

import company.board_project.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query(value = "select * from team where user_id = :userId", nativeQuery = true)
    List<Team> findByUserIdList(@Param("userId") long userId);

    @Query(value = "select * from team where team_name = :teamName", nativeQuery = true)
    Optional<Team> findByTeamName(@Param("teamName") String teamName);

    Optional<Team> findByUser_UserId(Long userId);

    @Query(value = "select * from team where match_id = :matchId", nativeQuery = true)
    List<Team> findByMatchId(@Param("matchId") long matchId);

    @Query(value = "select * from team where league_id = :leagueId", nativeQuery = true)
    List<Team> findAllTeamByLeagueId(@Param("leagueId") long leagueId);

    @Query(value = "select homeTeamName from team where match_id = :matchId", nativeQuery = true)
    List<Team> findByMatchHomeId(@Param("matchId") long matchId);

    @Query(value = "select AwayTeamName from team where match_id = :matchId", nativeQuery = true)
    List<Team> findByMatchAwayId(@Param("matchId") long matchId);

    // 명예 점수 상위 조회
    @Query(value = "SELECT * FROM team ORDER BY honor_score DESC", nativeQuery = true)
    List<Team> findByHighestHonorScore();

    // 명예 점수 하위 조회
    @Query(value = "SELECT * FROM team ORDER BY honor_score ASC", nativeQuery = true)
    List<Team> findByLowestHonorScore();

    Optional<Team> findByTeamId(Long teamId);

}
