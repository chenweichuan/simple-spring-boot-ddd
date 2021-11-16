package example.simple.springboot.ddd.libraries.utils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;

/**
 * @author chenweichuan
 */
public class CollectionUtil {
    public static <K, V> ImmutableMap<K, V> toImmutableMap(Map<? extends K, ? extends V> map) {
        if (map == null) {
            return null;
        } else if (map instanceof ImmutableMap) {
            return  (ImmutableMap)map;
        } else {
            ImmutableMap.Builder<K, V> immutableMapBuilder = new ImmutableMap.Builder<>();
            map.forEach((key, value) -> {
                if (key != null && value != null) {
                    immutableMapBuilder.put(key, value);
                }
            });
            return immutableMapBuilder.build();
        }
    }

    @SafeVarargs
    public static <K, V> Map<K, V> extendMap(Map<K, V> targetMap, Map<? extends K, ? extends V>... maps) {
        if (targetMap != null && maps != null) {
            for (Map<? extends K, ? extends V> map : maps) {
                if (map != null) {
                    targetMap.putAll(map);
                }
            }
        }
        return targetMap;
    }

    public static <E> ImmutableList<E> toImmutableList(List<? extends E> list) {
        if (list == null) {
            return null;
        } else if (list instanceof ImmutableList) {
            return  (ImmutableList)list;
        } else {
            return ImmutableList.copyOf(list);
        }
    }

    public static <E> boolean isEmptyList(List<E> list) {
        return (list == null) || (list.size() == 0);
    }
}
