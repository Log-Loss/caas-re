package caas.community.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/posts/comment")
    public List<Comment> getCommentsById(@RequestParam("postId") String id) {
        return commentService.getCommentsByPostId(id);
    }

    @RequestMapping(value = "/posts/comment/add", method = RequestMethod.POST)
    public void addComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
    }

    @RequestMapping(value = "/posts/comment", method = RequestMethod.DELETE)
    public void deleteComment(@RequestBody Comment comment) {
        commentService.deleteComment(comment);
    }

    @RequestMapping(value = "/posts/comment", method = RequestMethod.POST)
    public void updateComment(@RequestBody Comment comment) {
        commentService.updateComment(comment);
    }
}
