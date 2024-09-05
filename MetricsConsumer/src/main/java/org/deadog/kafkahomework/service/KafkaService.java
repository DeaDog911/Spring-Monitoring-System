package org.deadog.kafkahomework.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.deadog.kafkahomework.model.Metric;
import org.deadog.kafkahomework.model.MetricsHolder;
import org.deadog.kafkahomework.repository.MetricRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaService {
    private final MetricRepository metricRepository;

    private final MetricAnalysisService metricAnalysisService;

    @KafkaListener(id="metrics-consumer", topics="metrics-topic")
    public void consume(MetricsHolder metricsHolder) {
        for (Metric metric : metricsHolder.getMetrics()) {
           log.info("Получена метрика: {}", metric);
        }
        metricsHolder.setId(UUID.randomUUID().toString());
        metricsHolder.setTime(new Date(System.currentTimeMillis()));
        metricRepository.save(metricsHolder);

        metricAnalysisService.analyzeAllMetrics();
    }
}
