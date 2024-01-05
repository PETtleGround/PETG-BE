package cotato.hacakton.petg.service;


import cotato.hacakton.petg.domain.dto.TopicAddRequest;
import cotato.hacakton.petg.domain.entity.Member;
import cotato.hacakton.petg.domain.entity.Topic;
import cotato.hacakton.petg.repository.MemberRepository;
import cotato.hacakton.petg.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final MemberRepository memberRepository;
//    private final PostRepository postRepository;
//    private final LikeRepository likeRepository;

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


}
