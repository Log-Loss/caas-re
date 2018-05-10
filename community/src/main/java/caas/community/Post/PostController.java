package caas.community.Post;

import caas.community.Like.Like;
import caas.community.Like.LikeService;
import caas.community.Like.LikesList;
import caas.community.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private LikeService likeService;

    /* post section */
    @RequestMapping("/posts")
    public List<Post> getAllPosts() {
        List<Post> allPost = postService.getAllPosts();
        allPost.sort(new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {
                return o2.getTimeStamp().compareTo(o1.getTimeStamp());
            }
        });
        return allPost;
    }

    @RequestMapping("/posts/user")
    public List<Post> getPostsByUserId(@RequestParam("userId") String id){
        return postService.getPostsByUserId(id);
    }

    @RequestMapping("/posts/keyword")
    public List<Post> getPostByKeyword(@RequestParam("keyword") String keyword){
        return postService.getPostsByKeyword(keyword);
    }

    @RequestMapping("/posts/topic")
    public List<Post> getPostByTopic(@RequestParam("topic") String topic){
        return postService.getPostsByTopic(topic);
    }

    @RequestMapping("/posts/post")
    public Post getPostById(@RequestParam("postId") String id){
        return postService.getPostById(id);
    }

    @RequestMapping(value = "/posts/add", method = RequestMethod.POST)
    public void addPost(@RequestBody Post post){
        post.setPostId(Utils.generateRandomId());
        System.out.println(post);
        postService.addPost(post);
    }

    @RequestMapping(value = "/posts/post", method = RequestMethod.POST)
    public void updatePostById(@RequestParam("postId") String id, @RequestBody Post post){
        postService.updatePost(id, post);
    }

    @RequestMapping(value = "/posts/post", method = RequestMethod.DELETE)
    public void deletePostById(@RequestParam("postId") String id){
        postService.deletePost(id);
    }


    /* likes section */
    @RequestMapping(value = "/posts/like", method = RequestMethod.POST)
    public void addLike(@RequestBody Like like) {
        likeService.addLike(like);
    }

    @RequestMapping(value = "/posts/like", method = RequestMethod.DELETE)
    public void deleteLike(@RequestBody Like like) {
        likeService.deleteLike(like);
    }

    @RequestMapping("/posts/like")
    public LikesList getLikesList(@RequestParam("postId") String id) {
        LikesList likesList = new LikesList();
        likesList.setCount(likeService.getLikesCount(id));
        likesList.setLikeList(likeService.getLikesByPostId(id));

        return likesList;
    }
}