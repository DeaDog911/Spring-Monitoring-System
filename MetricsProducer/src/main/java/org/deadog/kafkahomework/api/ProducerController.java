package org.deadog.kafkahomework.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.deadog.kafkahomework.service.KafkaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/metrics")
@Tag(name="Producer Controller")
public class ProducerController {
    private final KafkaService kafkaService;

    @PostMapping
    @Operation(description = "Send spring actuator metrics to kafka")
    public void sendMetrics() {
        kafkaService.sendMetrics();
    }
}
