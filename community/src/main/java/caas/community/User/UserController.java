package caas.community.User;

import caas.community.Like.Like;
import caas.community.Comment.Comment;
import caas.community.Post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public User getUser(@RequestParam("userId") String id) {
        User user = new User(id);
        user.setComments(userService.getAllComments(id));
        user.setPosts(userService.getAllPosts(id));
        user.setLikes(userService.getAllLikes(id));
        return user;
    }


    @RequestMapping("/user/posts")
    public List<Post> getAllPosts(@RequestParam("userId") String id) {
        return userService.getAllPosts(id);
    }

    @RequestMapping("/user/comments")
    public List<Comment> getAllComments(@RequestParam("userId") String id) {
        return userService.getAllComments(id);
    }

    @RequestMapping("/user/likes")
    public List<Like> getAllLikes(@RequestParam("userId") String id) {
        return userService.getAllLikes(id);
    }

}
