package cotato.hacakton.petg.service;


import cotato.hacakton.petg.domain.dto.TopicAddRequest;
import cotato.hacakton.petg.domain.dto.TopicListResponse;
import cotato.hacakton.petg.domain.entity.Member;
import cotato.hacakton.petg.domain.entity.Post;
import cotato.hacakton.petg.domain.entity.Topic;
import cotato.hacakton.petg.repository.LikeRepository;
import cotato.hacakton.petg.repository.MemberRepository;
import cotato.hacakton.petg.repository.PostRepository;
import cotato.hacakton.petg.repository.TopicRepository;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public Long addTopic(TopicAddRequest topicAddRequest) {

        Long id = topicAddRequest.getMemberId();
        Member findMember = memberRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("회원이 아닙니다."));
        Topic topic = Topic.builder()
                .title(topicAddRequest.getTitle())
                .member(findMember)
                .build();
        Topic saveTopic = topicRepository.save(topic);
        return saveTopic.getId();
    }
    @Transactional(readOnly = true)
    public List<TopicListResponse> findAllTopics() {
        return topicRepository.findAllDescs().stream()
                .map(topic -> TopicListResponse.builder()
                        .id(topic.getId())
                        .title(topic.getTitle())
                        .url(topic.getUrl())
                        .build())
                .collect(Collectors.toList());
    }

    private List<PostLikeCount> LikeCountList(Long topicId){
        List<PostLikeCount> postLikeList = postRepository.findAll().stream()
                .map(post -> PostLikeCount.builder()
                        .postId(post.getId())
                        .likeCount(getLikeCount(post))
                        .build())
                .collect(Collectors.toList());
        Collections.sort(postLikeList, Collections.reverseOrder());
        return postLikeList;
    }

    private int getLikeCount(Post post) {
        return likeRepository.findAllByPostId(post.getId()).size();
    }
}
