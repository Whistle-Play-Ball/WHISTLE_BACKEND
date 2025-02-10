package company.board_project.user.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class UserListDto {
    List<UserResponseDto> userResponseDto;
}
