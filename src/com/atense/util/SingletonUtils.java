package com.atense.util;

/**
 * Singleton helper class for lazily initialization.
 * 
 * @author <a href="#" target="_blank">Kyle</a> 2015-11-04
 * 
 * @param <T>
 */
public abstract class SingletonUtils<T> {
    private T instance;

    protected abstract T newInstance();

    public final T getInstance() {
        if (instance == null) {
            synchronized (SingletonUtils.class) {
                if (instance == null) {
                    instance = newInstance();
                }
            }
        }
        return instance;
    }
}
