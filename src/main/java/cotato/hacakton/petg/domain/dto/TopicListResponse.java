package cotato.hacakton.petg.domain.dto;

import cotato.hacakton.petg.domain.entity.Topic;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TopicListResponse {

    private Long id;
    private String title;
    private String url;
}
