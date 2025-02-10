package company.board_project.comment.service;

import company.board_project.comment.entity.Comment;
import company.board_project.comment.repository.CommentRepository;
import company.board_project.content.entity.Content;
import company.board_project.content.service.ContentService;
import company.board_project.exception.BusinessLogicException;
import company.board_project.exception.Exceptions;
import company.board_project.user.entity.User;
import company.board_project.user.repository.UserRepository;
import company.board_project.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final ContentService contentService;

    /*
     * 댓글 등록
     */
    public Comment createComment(
            Comment comment,
            Long contentId,
            String email) {

        Content content = contentService.findContent(contentId);
        User user = userService.findUserByEmail(email);

        comment.setUser(user);
        comment.setContent(content);

        return commentRepository.save(comment);
    }

    /*
     * 댓글 수정
     */
    public Comment updateComment(Comment comment, Long commentId) {

        Comment findComment = findVerifiedComment(commentId); //ID로 멤버 존재 확인하고 comment 정보 반환

        User writer = userService.findUser(findComment.getUser().getUserId()); // 작성자 찾기
        if (userService.getLoginUser().getUserId() != writer.getUserId()) // 작성자와 로그인한 사람이 다를 경우
            throw new BusinessLogicException(Exceptions.UNAUTHORIZED);

        Optional.ofNullable(comment.getComment())
                .ifPresent(findComment::setComment);

        return commentRepository.save(findComment);
    }

    /*
     * 댓글 단건 조회
     */
    public Comment findComment(long commentId) {
        return findVerifiedComment(commentId);
    }

    /*
     * 댓글 전체 조회
     */
    public Page<Comment> findComments(int page, int size) {
        return commentRepository.findAll(PageRequest.of(page, size,
                Sort.by("commentId").descending()));
    }

    /*
     * 게시글 Id 단위 댓글 조회
     */
    public List<Comment> findContentComments(int contentId) {
        return findVerifiedContentComments(contentId);
    }

    /*
     * 댓글 삭제
     */
    public void deleteComment(long commentId) {
        Comment findComment = findVerifiedComment(commentId);

        User writer = userService.findUser(findComment.getUser().getUserId()); // 작성자 찾기
        if (userService.getLoginUser().getUserId() != writer.getUserId()) // 작성자와 로그인한 사람이 다를 경우
            throw new BusinessLogicException(Exceptions.UNAUTHORIZED);

        commentRepository.delete(findComment);
    }

    /*
     * 댓글 존재 유무 확인
     */
    public Comment findVerifiedComment(long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        Comment findComment =
                optionalComment.orElseThrow(() ->
                        new BusinessLogicException(Exceptions.COMMENT_NOT_FOUND));
        return findComment;
    }

    /*
     * 게시글 존재 유무 확인
     */
    public List<Comment> findVerifiedContentComments(long contentId) {
        List<Comment> findContentComments = commentRepository.findAllByContentId(contentId);

        return findContentComments;
    }
}