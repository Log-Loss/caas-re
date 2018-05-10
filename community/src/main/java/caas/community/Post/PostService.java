package caas.community.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;


    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(String id) {
        return postRepository.findPostByPostId(id);
    }

    public List<Post> getPostsByUserId(String id) {
        return postRepository.findPostByUserId(id);
    }

    public List<Post> getPostsByKeyword(String keyword) {
        return postRepository.findPostByKeywordsContains(keyword);
    }

    public List<Post> getPostsByTopic(String topic) {
        return postRepository.findPostByTopic(topic);
    }

    public void addPost(Post post) {
        postRepository.insert(post);
    }

    public void updatePost(String post_id, Post post) {
        Post pre_post = postRepository.findPostByPostId(post_id);
        if (pre_post != null) {
            pre_post.updatePost(post);
        }
        postRepository.save(pre_post);
    }

    public void deletePost(String id) {
        postRepository.delete(postRepository.findPostByPostId(id));
    }


}