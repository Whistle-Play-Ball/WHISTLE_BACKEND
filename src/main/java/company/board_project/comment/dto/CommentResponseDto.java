package company.board_project.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class CommentResponseDto {
    private Long commentId;
    private Long userId;
    private Long contentId;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    //유저 정보
    private String name;

    // 게시글 정보
    private String title;
}
