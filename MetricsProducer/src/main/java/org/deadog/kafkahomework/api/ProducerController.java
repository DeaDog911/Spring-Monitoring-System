package org.deadog.kafkahomework.api;

import lombok.RequiredArgsConstructor;
import org.deadog.kafkahomework.service.KafkaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/metrics")
public class ProducerController {
    private final KafkaService kafkaService;

    @PostMapping
    public void sendMetrics() {
        kafkaService.sendMetrics();
    }
}
