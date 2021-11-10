package example.simplespringbootddd.application.behaviors;

import example.simplespringbootddd.domain.exceptions.AbstractException;

/**
 * @author chenweichuan
 */
public interface BehaviorHandler<T,K> {
    /**
     * 执行行为
     * @param behavior 行为参数
     * @return K
     * @throws AbstractException 领域异常
     */
    public K handle(T behavior) throws AbstractException;
}
