package example.simple.springboot.ddd.application.dto.common;

import example.simple.springboot.ddd.libraries.utils.CollectionUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author chenweichuan
 */
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO<T> {
    private Long pageNo;
    private Long pageSize;
    private Long totalCount;
    private List<T> data;

    public List<T> getData() {
        return data = CollectionUtil.toImmutableList(data);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
