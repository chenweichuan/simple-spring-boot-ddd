package example.simplespringbootddd.application.commands;

import example.simplespringbootddd.domain.exceptions.AbstractException;

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
