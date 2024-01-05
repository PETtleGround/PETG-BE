package cotato.hacakton.petg.repository;

import cotato.hacakton.petg.domain.entity.Like;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {

    List<Like> findAllByPostId(Long postId);
}
