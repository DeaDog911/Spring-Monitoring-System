package org.deadog.kafkahomework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class MetricsProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetricsProducerApplication.class, args);
    }

}
