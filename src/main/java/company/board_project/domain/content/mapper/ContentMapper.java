package company.board_project.domain.content.mapper;

import company.board_project.domain.comment.dto.CommentResponseDto;
import company.board_project.domain.comment.entity.Comment;
import company.board_project.domain.comment.repository.CommentRepository;
import company.board_project.domain.content.dto.*;
import company.board_project.domain.content.entity.ContentFile;
import company.board_project.domain.content.repository.ContentFileRepository;
import company.board_project.domain.content.entity.Content;
import company.board_project.domain.user.entity.User;
import org.mapstruct.Mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Mapper(componentModel = "spring")
public interface ContentMapper {

    Content contentPostDtoToContent(ContentPostDto requestBody);

    Content contentPatchDtoToContent(ContentPatchDto requestBody);

    default ContentResponseDto contentToContentResponse(Content content, ContentFileRepository contentFileRepository){
        User user = content.getUser();
        List<ContentFile> contentFile = contentFileRepository.findByContentId(content.getContentId());

        return ContentResponseDto.builder()
                .contentId(content.getContentId())
                .userId(user.getUserId())
                .name(content.getName())
                .title(content.getTitle())
                .content(content.getContent())
                .categoryType(String.valueOf(content.getCategoryType()))
                .contentFileList(contentFile)
                .createdAt(content.getCreatedAt())
                .modifiedAt(content.getModifiedAt())
                .build();
    }

    default ContentAllResponseDto contentToContentAllResponse(Content content, ContentFileRepository contentFileRepository, CommentRepository commentRepository){
        User user = content.getUser();
        List<Comment> comments = commentRepository.findAllByContentId(content.getContentId());
        Collections.reverse(comments);

        return ContentAllResponseDto.builder()
                .contentId(content.getContentId())
                .userId(user.getUserId())
                .name(content.getName())
                .title(content.getTitle())
                .content(content.getContent())
                .categoryType(String.valueOf(content.getCategoryType()))
                .contentFileList(contentFileRepository.findByContentId(content.getContentId()))
                .comments(commentsToCommentResponseDtos(comments))
                .createdAt(content.getCreatedAt())
                .modifiedAt(content.getModifiedAt())
                .build();
    }

    default ContentListDto contentListDtoToContentResponse(List<Content> contents, ContentFileRepository contentFileRepository){

        return ContentListDto.builder()
                .contentResponseDto(contentsToContentsResponse(contents, contentFileRepository))
                .build();
    }

//  List<ContentResponseDto> contentsToContentsResponse(List<Content> contents, ContentFileRepository contentFileRepository);
    default List<ContentResponseDto> contentsToContentsResponse(List<Content> contents, ContentFileRepository contentFileRepository){
        return contents.stream()
                .map(content -> ContentResponseDto.builder()
                        .contentId(content.getContentId())
                        .userId(content.getUser().getUserId())
                        .name(content.getUser().getName())
                        .title(content.getTitle())
                        .content(content.getContent())
                        .categoryType(String.valueOf(content.getCategoryType()))
                        .contentFileList(contentFileRepository.findByContentId(content.getContentId()))
                        .createdAt(content.getCreatedAt())
                        .modifiedAt(content.getModifiedAt())
                        .build())
                .collect(Collectors.toList());
    }

    List<CommentResponseDto> commentsToCommentResponseDtos(List<Comment> comments);
}
