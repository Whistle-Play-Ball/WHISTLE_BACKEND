package company.board_project.content.dto;

import company.board_project.content.entity.ContentFile;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContentPatchDto {
    private long contentId;
    private long userId;
    private String title;
    @NotBlank(message = "게시글의 내용을 입력해야 합니다.")
    private String content;
    private String categoryType;
    private String boardCategory;
    public List<ContentFile> contentFileList;

    private String name;

    public void updateId(Long id){
        this.contentId = id;
    }
}
