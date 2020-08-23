package me.vitoremanoel.jmoduleloader.dependecy;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

public class DependencyManager extends LinkedHashMap<Class<?>, Object> {

    private static DependencyManager instance;
    public static DependencyManager getInstance(){
        if(DependencyManager.instance == null) DependencyManager.instance = new DependencyManager();
        return DependencyManager.instance;
    }

    private DependencyManager(){ }

    public void registerDependency(Class<?> type, Object value){
        super.put(type, value);
    }

    public Object getDependency(Class<?> type){
        return this.get(type);
    }

    public void injectDependency(Field field, Object classObject) throws IllegalAccessException{
        if(this.getDependency(field.getType()) == null) return;
        if(classObject == null) return;
        field.set(classObject, this.getDependency(field.getType()));
    }

}
