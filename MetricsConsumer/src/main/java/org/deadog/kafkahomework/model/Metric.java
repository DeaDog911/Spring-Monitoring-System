package org.deadog.kafkahomework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("metrics")
public class Metric {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MetricSample {
        private String name;

        private String value;
    }

    private String name;

    private String description;

    private List<Metric.MetricSample> samples;
}
