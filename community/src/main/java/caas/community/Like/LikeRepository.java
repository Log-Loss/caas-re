package caas.community.Like;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LikeRepository extends MongoRepository<Like, String> {

    public List<Like> getLikesByPostId(String postId);

    public List<Like> getLikesByUserId(String userId);

    public Like getLikeByUserIdAndPostId(String userId, String postId);
}
