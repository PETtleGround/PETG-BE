package cotato.hacakton.petg.controller;


import cotato.hacakton.petg.domain.dto.TopicAddRequest;
import cotato.hacakton.petg.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/topic")
public class TopicController {

    private final TopicService topicService;

    @PostMapping()
    public ResponseEntity<?> addTopic(@RequestBody TopicAddRequest addRequest) {
        Long topicId = topicService.addTopic(addRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(topicId);
    }

}
