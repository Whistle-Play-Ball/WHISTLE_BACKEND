package company.board_project.apply.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MatchApplyPostDto {
    private long userId;
    private long teamId;
    private String levelType;
    private String ageType;
    private String matchType;
}
