package caas.community.Like;

import org.springframework.data.annotation.Id;

public class Like {

    @Id
    private String id;

    private String userId;
    private String postId;

    public Like() {

    }

    public String getUserId() {
        return userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
