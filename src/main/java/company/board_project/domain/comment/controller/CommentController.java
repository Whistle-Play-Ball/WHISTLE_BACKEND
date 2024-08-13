package company.board_project.domain.comment.controller;

import company.board_project.common.resolver.AuthenticatedUser;
import company.board_project.domain.comment.dto.CommentPatchDto;
import company.board_project.domain.comment.dto.CommentPostDto;
import company.board_project.domain.comment.dto.CommentResponseDto;
import company.board_project.domain.comment.entity.Comment;
import company.board_project.domain.comment.mapper.CommentMapper;
import company.board_project.domain.comment.service.CommentService;
import company.board_project.response.MultiResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    /*
     * 댓글 생성
     */
    @PostMapping
    public ResponseEntity postComment(@Valid @RequestBody CommentPostDto requestBody, @AuthenticatedUser String email) {
        Comment comment = commentService.createComment(
                commentMapper.commentPostDtoToComment(requestBody),
                requestBody.getContentId(),
                email
        );
        CommentResponseDto commentResponseDto = commentMapper.commentToCommentResponseDto(comment);

        return ResponseEntity.ok(commentResponseDto);
    }

    /*
     * 댓글 수정
     */
    @PatchMapping("/{commentId}")
    public ResponseEntity patchComment(@Valid @RequestBody CommentPatchDto requestBody,
                                       @PathVariable("commentId") @Positive Long commentId) {
        Comment comment = commentService.updateComment(
                commentMapper.commentPatchDtoToComment(requestBody),
                commentId);

        comment.setCommentId(commentId);
        CommentResponseDto userResponseDto = commentMapper.commentToCommentResponseDto(comment);

        return ResponseEntity.ok(userResponseDto);
    }

    /*
     * 댓글 단건 조회
     */
    @GetMapping("/{commentId}")
    public ResponseEntity getComment(@PathVariable("commentId") @Positive Long commentId) {
        Comment comment = commentService.findComment(commentId);
        CommentResponseDto commentResponse = commentMapper.commentToCommentResponseDto(comment);

        return ResponseEntity.ok(commentResponse);
    }

    /*
     * 특정 게시글 ID에 있는 댓글 전체 조회
     */
    @GetMapping("/contents/{contentId}")
    public ResponseEntity getContentComments(@PathVariable("contentId") @Positive int contentId) {
        List<Comment> comments = commentService.findContentComments(contentId);
        List<CommentResponseDto> commentResponseDtos = commentMapper.contentCommentsToCommentResponseDtos(comments);
        return ResponseEntity.ok(commentResponseDtos);
    }

    /*
     * 댓글 전체 조회
     */
    @GetMapping
    public ResponseEntity getComments(@Positive @RequestParam("page") int page,
                                      @Positive @RequestParam("size") int size) {
        Page<Comment> pageComments = commentService.findComments(page - 1, size);
        List<Comment> comments = pageComments.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(commentMapper.commentsToCommentResponseDtos(comments),
                        pageComments),
                HttpStatus.OK);
    }

    /*
     * 댓글 삭제
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity deleteComment(@PathVariable("commentId") @Positive Long commentId) {
        commentService.deleteComment(commentId);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
