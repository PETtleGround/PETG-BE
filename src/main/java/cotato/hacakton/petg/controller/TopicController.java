package cotato.hacakton.petg.controller;


import cotato.hacakton.petg.domain.dto.TopicAddRequest;
import cotato.hacakton.petg.domain.dto.TopicListResponse;
import cotato.hacakton.petg.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/topic")
public class TopicController {

    private final TopicService topicService;

    @PostMapping("/add")
    public ResponseEntity<?> addTopic(@RequestBody TopicAddRequest addRequest) {
        Long topicId = topicService.addTopic(addRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(topicId);
    }

    @GetMapping()
    public ResponseEntity<?> showTopic(){
        List<TopicListResponse> allTopics = topicService.findAllTopics();
        return ResponseEntity.ok().body(allTopics);
    }

}
