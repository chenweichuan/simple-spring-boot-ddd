package example.simple.springboot.ddd.domain.exceptions;

import lombok.Getter;

/**
 * @author chenweichuan
 */
@Getter
public abstract class AbstractException extends Exception {
    protected Integer code = 101;

    public AbstractException(String message) {
        super(message);
    }
}
