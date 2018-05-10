package caas.community.Comment;

import caas.community.Utils.Utils;
import org.springframework.data.annotation.Id;

public class Comment {

    @Id
    private String id;

    private String commentId;
    private String userId;
    private String postId;
    private String content;

    private Long timeStamp;

    public Comment() {
        this.commentId = Utils.generateRandomId();
        this.timeStamp = Utils.getCurrentTime();
    }

    public String getCommentId() {
        return commentId;
    }

    public String getUserId() {
        return userId;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void updateComment(Comment newComment) {
        this.setContent(newComment.getContent());
        this.timeStamp = Utils.getCurrentTime();
    }


    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

}
