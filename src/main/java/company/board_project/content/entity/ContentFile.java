package company.board_project.content.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ContentFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentFileId;
    private Long contentId;
    private String contentFileUrl;

    public ContentFile(Long contentId, String contentFileUrl) {
        this.contentId = contentId;
        this.contentFileUrl = contentFileUrl;
    }
}
