package company.board_project.apply.repository;

import company.board_project.apply.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
public interface ApplyRepository extends JpaRepository<Apply,Long> {
    // applyId 단위 전체 조회
    @Query(value = "select * from applies where apply_id = :applyId", nativeQuery = true)
    List<Apply> findAllByApplyId(@Param("applyId") long applyId);

    // teamId 단위 조회
    @Query(value = "select * from applies where team_id = :teamId order by created_at desc", nativeQuery = true)
    List<Apply> findAllByTeamId(@Param("teamId") long teamId);

    // matchId 단위 조회
    @Query(value = "select * from applies where match_id = :matchId order by created_at desc", nativeQuery = true)
    List<Apply> findAllByMatchId(@Param("matchId") long matchId);

    // leagueId 단위 조회
    @Query(value = "select * from applies where league_id = :leagueId order by created_at desc", nativeQuery = true)
    List<Apply> findAllByLeagueId(@Param("leagueId") long leagueId);

    /*
     * applyId 단위 조회
     * findVerified를 위해 Optional<Apply>로 가져옴
     */
    Optional<Apply> findByApplyId(@Param("applyId") long applyId);
}
