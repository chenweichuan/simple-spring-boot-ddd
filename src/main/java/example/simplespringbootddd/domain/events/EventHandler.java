package example.simplespringbootddd.domain.events;


import example.simplespringbootddd.domain.exceptions.AbstractException;

/**
 * @author chenweichuan
 */
public interface EventHandler<T> {
    /**
     * 执行事件监听
     * @param event 事件参数
     * @throws AbstractException
     */
    public void handle(T event) throws AbstractException;
}
