package me.vitoremanoel.jmoduleloader.module;

import me.vitoremanoel.jmoduleloader.annotations.Inject;
import me.vitoremanoel.jmoduleloader.dependecy.DependencyManager;

import java.lang.reflect.Field;

public class JModule {

    private String name;
    private String version;
    private String description;

    private Class<?> mainClass;
    private Object mainObject;
    private ModuleListenerManager listenerManager = new ModuleListenerManager();

    public JModule(String name, String version, String description, Object mainObject){
        this.name = name;
        this.version = version;
        this.description = description;
        this.mainObject = mainObject;
        this.mainClass = mainObject.getClass();
        this.registerListener(this.mainObject);
        this.loadModuleDependecy();
        this.loadDependency();
    }

    public Class<?> getMainClass() {
        return this.mainClass;
    }

    public ModuleListenerManager getListenerManager(){
        return this.listenerManager;
    }

    public void registerListener(Object listenerObject){
        this.getListenerManager().registerModuleEventHandler(new ModuleEventHandler(listenerObject.getClass(), listenerObject));
    }

    protected void loadModuleDependecy() {
        for(Field field : this.mainClass.getDeclaredFields()){
            if(!field.isAnnotationPresent(Inject.class)) return;
            if(field.getType() != JModule.class) continue;
            try {
                field.setAccessible(true);
                field.set(this.mainObject, this);
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }
    }

    protected void loadDependency(){
        for(Field field : this.mainClass.getDeclaredFields()){
            if(!field.isAnnotationPresent(Inject.class)) return;
            field.setAccessible(true);
            try {
                DependencyManager.getInstance().injectDependency(field, this.mainObject);
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }
    }
}
