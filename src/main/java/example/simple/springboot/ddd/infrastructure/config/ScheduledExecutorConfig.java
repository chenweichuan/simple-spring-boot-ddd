package example.simple.springboot.ddd.infrastructure.config;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author chenweichuan
 */
@Configuration
public class ScheduledExecutorConfig {
    @Bean
    public ScheduledExecutorService scheduledExecutorService() {
        return new ScheduledThreadPoolExecutor(
                5,
                new BasicThreadFactory.Builder().namingPattern("async-task-schedule-pool-%d").daemon(true).build()
        );
    }
}
