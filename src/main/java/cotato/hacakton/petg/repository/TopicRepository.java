package cotato.hacakton.petg.repository;

import cotato.hacakton.petg.domain.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    @Query("SELECT t FROM Topic t ORDER BY t.localDate DESC")
    List<Topic> findAllDescs();
}
