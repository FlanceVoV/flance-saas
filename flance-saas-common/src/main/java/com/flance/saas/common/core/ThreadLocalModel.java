package com.flance.saas.common.core;

import lombok.Data;
import lombok.NonNull;

@Data
public class ThreadLocalModel<T> {

    @NonNull
    private ThreadLocal<T> threadLocal;

    public T get() {
        return threadLocal.get();
    }

    public void put(T t) {
        threadLocal.set(t);
    }

    public void remove() {
        threadLocal.remove();
    }

    public static <S> S create() {
        return (S) new ThreadLocalModel<S>(new ThreadLocal<>());
    }

}
