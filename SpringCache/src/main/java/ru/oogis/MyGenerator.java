package ru.oogis;

import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

public class MyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object o, Method method, Object... objects) {
        return o.toString()+method;
    }
}
