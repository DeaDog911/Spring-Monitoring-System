package org.deadog.kafkahomework.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.deadog.kafkahomework.model.Metric;
import org.deadog.kafkahomework.service.MetricService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/metrics")
@Tag(name="Consumer Controller")
public class ConsumerController {
    private final MetricService metricService;

    @GetMapping
    @Operation(description = "Get last received metrics names")
    public List<String> getMetrics() {
        return metricService.getMetrics();
    }

    @GetMapping("/{name}")
    @Operation(description = "Get last received metric by name")
    public Metric getMetrics(@PathVariable String name) {
        return metricService.getMetric(name);
    }
}
