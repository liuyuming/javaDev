package com.thruman.java.common.util.redisUtil;

public interface Function<E,T> {
    public T callback(E e);
}
