package cotato.hacakton.petg.service;

import lombok.Builder;

@Builder
public class PostLikeCount implements Comparable<PostLikeCount> {

    private Long postId;
    private Integer likeCount;

    @Override
    public int compareTo(PostLikeCount o) {
        if(o.likeCount < likeCount){
            return 1;
        }
        else if(o.likeCount > likeCount){
            return -1;
        }
        return 0;
    }
}