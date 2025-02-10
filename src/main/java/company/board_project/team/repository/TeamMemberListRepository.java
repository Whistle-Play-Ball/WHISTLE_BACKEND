package company.board_project.team.repository;

import company.board_project.team.entity.TeamMemberList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamMemberListRepository extends JpaRepository<TeamMemberList, Long> {
    Optional<TeamMemberList> findByTeam_TeamId(Long teamId);
    Optional<TeamMemberList> findByUser_UserId(Long userId);
}
