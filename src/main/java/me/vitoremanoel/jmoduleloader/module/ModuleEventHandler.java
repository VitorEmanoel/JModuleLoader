package me.vitoremanoel.jmoduleloader.module;

public class ModuleEventHandler {

    private Class<?> handlerClass;
    private Object handlerObject;

    public ModuleEventHandler(Class<?> handlerClass, Object handlerObject) {
        this.handlerClass = handlerClass;
        this.handlerObject = handlerObject;
    }

    public Class<?> getHandlerClass() {
        return this.handlerClass;
    }

    public Object getHandlerObject(){
        return this.handlerObject;
    }
}
