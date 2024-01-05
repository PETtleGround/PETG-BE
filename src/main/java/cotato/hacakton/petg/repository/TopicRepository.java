package cotato.hacakton.petg.repository;

import cotato.hacakton.petg.domain.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
