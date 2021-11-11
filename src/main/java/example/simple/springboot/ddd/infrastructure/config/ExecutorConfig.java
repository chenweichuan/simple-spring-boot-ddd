package example.simple.springboot.ddd.infrastructure.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chenweichuan
 */
@Configuration
public class ExecutorConfig {
    @Bean
    public ExecutorService executorService() {
        return new ThreadPoolExecutor(
                20,
                20,
                1000 * 60,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(1024),
                new ThreadFactoryBuilder().setNameFormat("async-task-pool-%d").build(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }
}
