package example.simple.springboot.ddd.application.dto.common;

import lombok.*;

import java.io.Serializable;

/**
 * @author chenweichuan
 */
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ResultWrapper<T> {
    private static final long serialVersionUID = 1L;
    /**
     * 返回的编码
     */
    @Builder.Default
    private Integer code = 0;
    /**
     * 返回的信息
     */
    private String message;
    /**
     * 返回的对象
     */
    private T data = null;
}