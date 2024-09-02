package org.deadog.kafkahomework.api;

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
public class ConsumerController {
    private final MetricService metricService;

    @GetMapping
    public List<String> getMetrics() {
        return metricService.getMetrics();
    }

    @GetMapping("/{name}")
    public Metric getMetrics(@PathVariable String name) {
        return metricService.getMetric(name);
    }
}
