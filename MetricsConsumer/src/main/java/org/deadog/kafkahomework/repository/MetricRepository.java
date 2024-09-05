package org.deadog.kafkahomework.repository;

import org.deadog.kafkahomework.model.MetricsHolder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetricRepository extends MongoRepository<MetricsHolder, String> {
    @Query(value = "{}", sort = "{ 'time' : -1 }")
    List<MetricsHolder> findAllSortedByTime();
}
