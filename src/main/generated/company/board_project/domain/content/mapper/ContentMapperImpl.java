package company.board_project.domain.content.mapper;

import company.board_project.constant.CategoryType;
import company.board_project.domain.comment.dto.CommentResponseDto;
import company.board_project.domain.comment.entity.Comment;
import company.board_project.domain.content.dto.ContentPatchDto;
import company.board_project.domain.content.dto.ContentPostDto;
import company.board_project.domain.content.entity.Content;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-12T11:00:42+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (JetBrains s.r.o.)"
)
@Component
public class ContentMapperImpl implements ContentMapper {

    @Override
    public Content contentPostDtoToContent(ContentPostDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Content content = new Content();

        content.setTitle( requestBody.getTitle() );
        content.setContent( requestBody.getContent() );
        content.setName( requestBody.getName() );
        if ( requestBody.getCategoryType() != null ) {
            content.setCategoryType( Enum.valueOf( CategoryType.class, requestBody.getCategoryType() ) );
        }

        return content;
    }

    @Override
    public Content contentPatchDtoToContent(ContentPatchDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Content content = new Content();

        content.setContentId( requestBody.getContentId() );
        content.setTitle( requestBody.getTitle() );
        content.setContent( requestBody.getContent() );
        content.setName( requestBody.getName() );
        if ( requestBody.getCategoryType() != null ) {
            content.setCategoryType( Enum.valueOf( CategoryType.class, requestBody.getCategoryType() ) );
        }

        return content;
    }

    @Override
    public List<CommentResponseDto> commentsToCommentResponseDtos(List<Comment> comments) {
        if ( comments == null ) {
            return null;
        }

        List<CommentResponseDto> list = new ArrayList<CommentResponseDto>( comments.size() );
        for ( Comment comment : comments ) {
            list.add( commentToCommentResponseDto( comment ) );
        }

        return list;
    }

    protected CommentResponseDto commentToCommentResponseDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentResponseDto.CommentResponseDtoBuilder commentResponseDto = CommentResponseDto.builder();

        commentResponseDto.commentId( comment.getCommentId() );
        commentResponseDto.comment( comment.getComment() );
        commentResponseDto.createdAt( comment.getCreatedAt() );
        commentResponseDto.modifiedAt( comment.getModifiedAt() );
        commentResponseDto.name( comment.getName() );

        return commentResponseDto.build();
    }
}
