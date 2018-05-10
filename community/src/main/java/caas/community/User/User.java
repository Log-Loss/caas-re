package caas.community.User;

import caas.community.Like.Like;
import caas.community.Comment.Comment;
import caas.community.Post.Post;

import java.util.List;

public class User {
    private String userId;
    private List<Post> posts;
    private List<Comment> comments;
    private List<Like> likes;

    public User() {

    }

    public User(String id) {
        this.userId = id;
    }

    public String getUserId() {
        return userId;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likeList) {
        this.likes = likeList;
    }

}

