package company.board_project.team.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamMemberInfoPostDto {
    private Long userId;
    private Long teamId;
    private String name;
    private String teamMemberRole;

    @Override
    public String toString() {
        return "TeamMemberInfoPostDto{" +
                "userId=" + userId +
                ", teamId=" + teamId +
                ", name='" + name + '\'' +
                ", teamMemberRole='" + teamMemberRole + '\'' +
                '}';
    }
}
