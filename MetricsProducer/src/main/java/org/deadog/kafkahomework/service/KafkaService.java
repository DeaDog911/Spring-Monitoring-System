package org.deadog.kafkahomework.service;

import lombok.RequiredArgsConstructor;
import org.deadog.kafkahomework.model.Metric;
import org.deadog.kafkahomework.model.MetricsHolder;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaService {
    private final KafkaTemplate<Object, Object> kafkaTemplate;

    private final MetricsEndpoint metricsEndpoint;

    public void sendMetrics() {
//        List<String> metricsNames = Arrays.asList(
//                "process.cpu.usage",
//                "system.cpu.usage",
//                "http.server.requests",
//                "jvm.memory.used",
//                "jvm.memory.max",
//                "jwm.memory.committed"
//        );
        List<Metric> metrics = metricsEndpoint.listNames().getNames().stream()
                .map(name -> metricsEndpoint.metric(name, null))
                .filter(metric -> metric != null)
                .map(Metric::new)
                .toList();
        MetricsHolder metricsHolder = new MetricsHolder(metrics);
        kafkaTemplate.send("metrics-topic", metricsHolder);
    }
}
