package company.board_project.team.repository;

import company.board_project.team.entity.TeamMemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamMemberInfoRepository extends JpaRepository<TeamMemberInfo, Long> {
    Optional<TeamMemberInfo> findByTeam_TeamId(Long teamId);
    Optional<TeamMemberInfo> findByUser_UserId(Long userId);
}
