package example.simple.springboot.ddd.anticorruption.infrastructure;

import example.simple.springboot.ddd.domain.events.Event;
import example.simple.springboot.ddd.infrastructure.dependencies.MessagePublisherService;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chenweichuan
 */
@Service
public class MessagePublisherServiceImpl implements MessagePublisherService {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    final private static ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(
            5,
            new BasicThreadFactory.Builder().namingPattern("async-task-schedule-pool-%d").daemon(true).build()
    );

    @Override
    public void publishDomainEvent(Event event) {
        applicationEventPublisher.publishEvent(event);
    }

    @Override
    public void invokeTask(Runnable task, int delayInSeconds) {
        executorService.schedule(task, delayInSeconds, TimeUnit.SECONDS);
    }

    @Override
    public void invokeTask(Runnable task) {
        invokeTask(task, 0);
    }
}
