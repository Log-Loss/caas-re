package caas.community.Post;

import caas.community.Utils.Utils;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Post {

    // for mongoDB
    @Id
    private String id;

    private String postId;
    private String userId;
    private String title;
    private String topic;
    private List<String> keywords;
    private String content;

    private Long timeStamp;

    public Post() {
        this.postId = Utils.generateRandomId();
        this.timeStamp = Utils.getCurrentTime();
    }


    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void generatePost_id() {
        this.postId = Utils.generateRandomId();
    }

    public String getUserId() {
        return userId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }


    public Long getTimeStamp() {
        return timeStamp;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public void addKeywords(String keyword) {
        this.keywords.add(keyword);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void updatePost(Post post) {
        this.setContent(post.getContent());
        this.setTitle(post.getTitle());
        this.setKeywords(post.getKeywords());
        this.setTopic(post.getTopic());
        this.timeStamp = Utils.getCurrentTime();
    }
}
