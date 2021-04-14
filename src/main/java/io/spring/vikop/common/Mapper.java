package io.spring.vikop.common;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface Mapper<E, T> {


    T toDto(E e);

    default List<T> toDtoList(List<E> e){
        return e.stream().map(this::toDto).collect(Collectors.toList());
    }

    default List<T> toDtoList(Set<E> e){
        return e.stream().map(this::toDto).collect(Collectors.toList());
    }

}
