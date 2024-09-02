package org.deadog.kafkahomework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;

import java.util.List;

@Data
@NoArgsConstructor
public class Metric {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class MetricSample {
        private String name;

        private String value;
    }

    private String name;

    private String description;

    private List<MetricSample> samples;

    public Metric(MetricsEndpoint.MetricDescriptor descriptor) {
        this.name = descriptor.getName();
        this.description = descriptor.getDescription();
        this.samples = descriptor.getMeasurements().stream()
                .map(measurement ->
                        new MetricSample(measurement.getStatistic().name(),
                                measurement.getValue().toString()))
                .toList();
    }
}