package com.atense.utils;

/**
 * Singleton helper class for lazily initialization.
 * 
 * @author <a href="#" target="_blank">Kyle</a> 2015-11-04
 * 
 * @param <T>
 */
public abstract class SingletonUtil<T> {
    private T instance;

    protected abstract T newInstance();

    public final T getInstance() {
        if (instance == null) {
            synchronized (SingletonUtil.class) {
                if (instance == null) {
                    instance = newInstance();
                }
            }
        }
        return instance;
    }
}
