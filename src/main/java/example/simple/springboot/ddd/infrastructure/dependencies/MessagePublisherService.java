package example.simple.springboot.ddd.infrastructure.dependencies;

import example.simple.springboot.ddd.domain.events.Event;

/**
 * @author chenweichuan
 */
public interface MessagePublisherService extends Service {
    /**
     * 发布领域事件
     * @param event 领域事件
     */
    public void publishDomainEvent(Event event);

    /**
     * 发起异步处理
     * @param task 任务
     * @param delayInSeconds 延迟时间
     */
    public void invokeTask(Runnable task, int delayInSeconds);

    /**
     * 发起异步处理
     * @param task 任务
     */
    public void invokeTask(Runnable task);
}
