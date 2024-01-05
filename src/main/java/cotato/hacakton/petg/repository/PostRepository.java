package cotato.hacakton.petg.repository;

import cotato.hacakton.petg.domain.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByTopicId(Long topicId);
}
