package me.vitoremanoel.modulejar.dependecy;

import java.util.LinkedHashMap;

public class DependencyManager extends LinkedHashMap<Class<?>, Object> {

    public void registerDependency(Class<?> type, Object value){
        super.put(type, value);
    }

    public Object getDependency(Class<?> type){
        return this.get(type);
    }

}
