package company.board_project.apply.dto;

import company.board_project.constant.AcceptType;
import company.board_project.constant.LevelType;
import company.board_project.constant.MatchType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class TeamApplyResponseDto {
    private Long applyId;
    private Long userId;
    private Long teamId;
    private Integer age;
    private String applierName;
    private String applyMessage;
    private LevelType levelType;
    private AcceptType acceptType;
    private MatchType matchType;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
