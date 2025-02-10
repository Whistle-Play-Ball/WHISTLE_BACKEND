package company.board_project.content.dto;

import company.board_project.content.entity.ContentFile;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentResponseDto {
    private Long contentId;
    private Long userId;
    private String title;
    private String content;
    private String categoryType;
    private String boardCategory;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public List<ContentFile> contentFileList;
    private String name;
}
