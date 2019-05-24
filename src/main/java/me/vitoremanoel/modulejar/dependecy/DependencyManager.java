package me.vitoremanoel.modulejar.dependecy;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

public class DependencyManager extends LinkedHashMap<Class<?>, Object> {

    private static DependencyManager instance;

    private DependencyManager() {
    }

    public static DependencyManager getInstance() {
        if (DependencyManager.instance == null)
            DependencyManager.instance = new DependencyManager();
        return DependencyManager.instance;
    }

    public Object getDependency(Class<?> type) {
        return this.get(type);
    }

    public void registerDependency(Class<?> type, Object value) {
        super.put(type, value);
    }

    public void injectDependency(Field field, Object classObject) throws IllegalAccessException {
        if (classObject == null) return;
        if (this.getDependency(field.getType()) == null) return;

        field.set(classObject, this.getDependency(field.getType()));
    }

}
