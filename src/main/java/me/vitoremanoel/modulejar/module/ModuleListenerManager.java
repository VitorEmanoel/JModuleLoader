package me.vitoremanoel.modulejar.module;

import me.vitoremanoel.modulejar.annotations.Listener;
import me.vitoremanoel.modulejar.listeners.Event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

public class ModuleListenerManager {

    private LinkedList<ModuleEventHandler> eventHandlers = new LinkedList<>();

    public LinkedList<ModuleEventHandler> getEventHandlers() {
        return this.eventHandlers;
    }

    public void registerModuleEventHandler(ModuleEventHandler event) {
        this.eventHandlers.add(event);
    }

    public void call(Event event) {
        try {
            for (ModuleEventHandler eventHandler : this.eventHandlers) {
                for (Method method : eventHandler.getHandlerClass().getMethods()) {
                    if (!method.isAnnotationPresent(Listener.class)) return;
                    if (method.getParameterCount() != 1) return;
                    if (!(method.getParameterTypes()[0].equals(event.getClass()))) return;
                    method.invoke(eventHandler.getHandlerObject(), event);
                }
            }
        } catch (IllegalAccessException | InvocationTargetException ignored) {

        }
    }


}
