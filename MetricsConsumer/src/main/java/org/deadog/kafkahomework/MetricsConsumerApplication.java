package org.deadog.kafkahomework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MetricsConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetricsConsumerApplication.class, args);
    }

}
