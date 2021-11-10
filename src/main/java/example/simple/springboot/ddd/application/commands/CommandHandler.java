package example.simple.springboot.ddd.application.commands;

import example.simple.springboot.ddd.domain.exceptions.AbstractException;

/**
 * @author chenweichuan
 */
public interface CommandHandler<T,K> {

    /**
     * 执行命令
     * @param command 命令参数
     * @return K
     * @throws AbstractException 领域异常
     */
    public K handle(T command) throws AbstractException;
}
