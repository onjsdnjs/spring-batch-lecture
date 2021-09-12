package io.springbatch.springbatchlecture.batch.domain;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SharedObject {

    private Map<String, Object> sharedObjects = new ConcurrentHashMap<>();

    public Object getSharedObject(String key) {
        return sharedObjects.get(key);
    }

    public void setSharedObject(String key, Object value) {
        this.sharedObjects.put(key, value);
    }
}
