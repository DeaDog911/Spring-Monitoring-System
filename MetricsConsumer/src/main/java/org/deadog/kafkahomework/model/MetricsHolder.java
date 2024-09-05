package org.deadog.kafkahomework.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document("metrics")
public class MetricsHolder {

    @Id
    private String id;

    private Date time;

    private List<Metric> metrics;
}
