package company.board_project.apply.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplyListDto {
    private List<ApplyResponseDto> applyResponseDtoList;
}
