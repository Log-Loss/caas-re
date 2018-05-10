package caas.community.User;

import caas.community.Like.Like;
import caas.community.Like.LikeService;
import caas.community.Comment.Comment;
import caas.community.Comment.CommentService;
import caas.community.Post.Post;
import caas.community.Post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    CommentService commentService;

    @Autowired
    PostService postService;

    @Autowired
    LikeService likeService;

    public List<Comment> getAllComments(String userId) {
        return commentService.getCommentsByUserId(userId);
    }

    public List<Post> getAllPosts(String userId) {
        return postService.getPostsByUserId(userId);
    }

    public List<Like> getAllLikes(String userId) {
        return likeService.getLikesByUserId(userId);
    }

}
