package company.board_project.content.dto;

import company.board_project.content.entity.ContentFile;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ContentPostDto {
    @NotBlank(message = "게시글의 제목을 입력해야 합니다.")
    private String title;
    @NotBlank(message = "게시글의 내용을 입력해야 합니다.")
    private String content;
    private String categoryType;
    private String boardCategory;
    public List<ContentFile> contentFileList;

    private String name;
}
