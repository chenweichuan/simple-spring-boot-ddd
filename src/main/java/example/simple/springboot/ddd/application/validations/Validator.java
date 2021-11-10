package example.simple.springboot.ddd.application.validations;

import example.simple.springboot.ddd.domain.exceptions.AbstractException;

/**
 * @author chenweichuan
 */
public interface Validator<T> {
    /**
     * 检测命令有效性
     * @param command 命令的参数
     * @throws AbstractException 领域异常
     */
    public void check(T command) throws AbstractException;
}
