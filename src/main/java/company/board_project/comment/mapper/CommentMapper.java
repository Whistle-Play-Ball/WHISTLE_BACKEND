package company.board_project.comment.mapper;

import company.board_project.comment.dto.CommentPatchDto;
import company.board_project.comment.dto.CommentPostDto;
import company.board_project.comment.dto.CommentResponseDto;
import company.board_project.comment.entity.Comment;
import company.board_project.content.entity.Content;
import company.board_project.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment commentPostDtoToComment(CommentPostDto requestBody);

    Comment commentPatchDtoToComment(CommentPatchDto requestBody);

    default CommentResponseDto commentToCommentResponseDto(Comment comment) {
        User user = comment.getUser();
        Content content = comment.getContent();

        return CommentResponseDto.builder()
                .commentId(comment.getCommentId())
                .userId(user.getUserId())
                .name(user.getName())
                .contentId(content.getContentId())
                .title(content.getTitle())
                .comment(comment.getComment())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
    }
    List<CommentResponseDto> commentsToCommentResponseDtos(List<Comment> comment);
    List<CommentResponseDto> contentCommentsToCommentResponseDtos(List<Comment> comment);
}