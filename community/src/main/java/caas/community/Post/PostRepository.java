package caas.community.Post;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PostRepository extends MongoRepository<Post, String> {

    Post findPostByPostId(String post_id);

    List<Post> findPostByUserId(String user_id);

    List<Post> findPostByKeywordsContains(String keyword);

    List<Post> findPostByTopic(String topic);
}
