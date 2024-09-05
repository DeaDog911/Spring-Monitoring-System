package org.deadog.kafkahomework.service;

import lombok.RequiredArgsConstructor;
import org.deadog.kafkahomework.model.Metric;
import org.deadog.kafkahomework.repository.MetricRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MetricService {
    private final MetricRepository metricRepository;

    public List<String> getMetrics() {
        return metricRepository.findAllSortedByTime().stream().findFirst()
                .map(holder -> holder.getMetrics().stream().map(Metric::getName).toList())
                .orElseGet(LinkedList::new);
    }

    public Metric getMetric(String name) {
        return metricRepository.findAllSortedByTime().stream().findFirst()
                .flatMap(holder -> holder.getMetrics().stream()
                        .filter(metric -> metric.getName().equals(name))
                        .findFirst())
                .orElse(null);
    }
}
