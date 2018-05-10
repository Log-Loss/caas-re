package caas.community.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getCommentsByUserId(String id) {
        return commentRepository.findCommentsByUserId(id);
    }

    public List<Comment> getCommentsByPostId(String id) {
        return commentRepository.findCommentsByPostId(id);
    }

    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    public void deleteComment(Comment comment) {
        Comment pre_comment = commentRepository.findCommentByCommentId(comment.getCommentId());
        commentRepository.delete(pre_comment);
    }

    public void updateComment(Comment comment) {
        Comment pre_comment = commentRepository.findCommentByCommentId(comment.getCommentId());
        pre_comment.updateComment(comment);

        commentRepository.save(pre_comment);
    }
}
