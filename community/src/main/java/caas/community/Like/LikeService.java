package caas.community.Like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    public List<Like> getLikesByUserId(String id) {
        return likeRepository.getLikesByUserId(id);
    }

    public List<Like> getLikesByPostId(String id) {
        return likeRepository.getLikesByPostId(id);
    }

    public void addLike(Like like) {
        if (likeRepository.getLikeByUserIdAndPostId(like.getUserId(), like.getPostId()) == null){
            likeRepository.save(like);
        }
    }

    public void deleteLike(Like like) {
        likeRepository.delete(likeRepository.getLikeByUserIdAndPostId(like.getUserId(), like.getPostId()));
    }

    public int getLikesCount(String postId) {
        List<Like> likeList = likeRepository.getLikesByPostId(postId);
        if (likeList == null) {
            return 0;
        }
        return likeList.size();
    }
}
