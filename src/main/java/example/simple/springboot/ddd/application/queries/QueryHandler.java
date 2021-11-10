package example.simple.springboot.ddd.application.queries;

import example.simple.springboot.ddd.domain.exceptions.AbstractException;

public interface QueryHandler<T,K> {
    /**
     * 执行查询
     * @param query 查询参数
     * @return K
     */
    public K handle(T query) throws AbstractException;
}
