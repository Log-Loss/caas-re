package caas.community.Comment;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommentRepository extends MongoRepository<Comment, String> {

    public List<Comment> findCommentsByUserId(String id);

    public List<Comment> findCommentsByPostId(String id);

    public Comment findCommentByCommentId(String id);
}
