package org.deadog.kafkahomework.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.deadog.kafkahomework.model.Metric;
import org.deadog.kafkahomework.model.MetricsHolder;
import org.deadog.kafkahomework.repository.MetricRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MetricAnalysisService {

    private final MetricRepository metricRepository;

    // Основной метод для анализа всех метрик
    public void analyzeAllMetrics() {
        List<MetricsHolder> allMetrics = metricRepository.findAll();

        // Группируем метрики по их имени и ключам
        Map<String, Map<String, List<Double>>> metricsByNameAndKey = groupMetricsByNameAndKey(allMetrics);

        // Выполняем анализ для каждой группы метрик по ключам
        for (Map.Entry<String, Map<String, List<Double>>> entry : metricsByNameAndKey.entrySet()) {
            String metricName = entry.getKey();
            Map<String, List<Double>> samplesByKey = entry.getValue();

            log.info("Анализ метрик для: {}", metricName);

            for (Map.Entry<String, List<Double>> sampleEntry : samplesByKey.entrySet()) {
                String key = sampleEntry.getKey();
                List<Double> values = sampleEntry.getValue();

                // Рассчитываем основные статистики
                double average = calculateAverage(values);
                double max = Collections.max(values);
                double min = Collections.min(values);

                log.info("Ключ: {}, Среднее: {}, Макс.: {}, Мин.: {}", key, average, max, min);

                // Проверка на аномалии
                detectAnomalies(key, values, average);
            }
        }
    }

    // Группировка метрик по имени и ключам
    private Map<String, Map<String, List<Double>>> groupMetricsByNameAndKey(List<MetricsHolder> allMetrics) {
        Map<String, Map<String, List<Double>>> groupedMetrics = new HashMap<>();

        for (MetricsHolder holder : allMetrics) {
            for (Metric metric : holder.getMetrics()) {
                String metricName = metric.getName();

                // Инициализируем группу для текущей метрики, если еще не создана
                groupedMetrics.putIfAbsent(metricName, new HashMap<>());

                // Группируем значения по ключам из MetricSample
                for (Metric.MetricSample sample : metric.getSamples()) {
                    String key = sample.getName();
                    double value = parseSampleValue(sample.getValue());

                    // Добавляем значение в соответствующую группу по ключу
                    groupedMetrics.get(metricName)
                            .computeIfAbsent(key, k -> new ArrayList<>())
                            .add(value);
                }
            }
        }

        return groupedMetrics;
    }

    // Метод для вычисления среднего значения списка
    private double calculateAverage(List<Double> values) {
        return values.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    // Обработка строки значения sample в числовое значение
    private double parseSampleValue(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            log.warn("Неверный формат значения в образце метрики: {}", value);
            return 0.0; // Можно настроить более сложную обработку ошибок
        }
    }

    // Метод для выявления аномалий по ключам
    private void detectAnomalies(String key, List<Double> values, double average) {
        double threshold = average * 1.5; // Пример: пороговое значение 150% от среднего

        for (double value : values) {
            if (value > threshold) {
                log.warn("Обнаружена аномалия для ключа {} со значением {}. Превышение порога {}.", key, value, threshold);
            }
        }
    }
}
