package me.vitoremanoel.jmoduleloader.module;

import me.vitoremanoel.jmoduleloader.annotations.Listener;
import me.vitoremanoel.jmoduleloader.listeners.Event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

public class ModuleListenerManager {

    private LinkedList<ModuleEventHandler> eventHandlers = new LinkedList<>();

    public LinkedList<ModuleEventHandler> getEventHandlers() {
        return this.eventHandlers;
    }

    public void registerModuleEventHandler(ModuleEventHandler event){
        this.eventHandlers.add(event);
    }

    public void call(Event event){
        try {
            for (ModuleEventHandler eventHandler : this.eventHandlers) {
                for (Method method : eventHandler.getHandlerClass().getMethods()) {
                    if (!method.isAnnotationPresent(Listener.class)) continue;
                    if (method.getParameterCount() != 1) continue;
                    if (!(method.getParameterTypes()[0].equals(event.getClass()))) continue;
                    method.invoke(eventHandler.getHandlerObject(), event);
                }
            }
        }catch(IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
        }
    }


}
