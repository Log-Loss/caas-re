package caas.community.Like;

import java.util.List;

public class LikesList {
    private int count;
    private List<Like> likeList;

    public LikesList() {

    }

    public LikesList(int count, List<Like> likeList) {
        this.count = count;
        this.likeList = likeList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Like> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<Like> likeList) {
        this.likeList = likeList;
    }
}
