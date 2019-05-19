package me.vitoremanoel.modulejar.listeners;

import me.vitoremanoel.modulejar.annotations.Listener;
import me.vitoremanoel.modulejar.module.JModule;
import me.vitoremanoel.modulejar.module.ModuleEventHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ListenerManager {

    private static ListenerManager instance;

    public static ListenerManager getInstance(){
        if(ListenerManager.instance == null) ListenerManager.instance = new ListenerManager();
        return ListenerManager.instance;
    }

    private ListenerManager() { }

    public void call(JModule module, Event event) throws IllegalAccessException, InvocationTargetException {
        for(ModuleEventHandler eventHandler : module.getListenerManager().getEventHandlers()) {

        }
    }
}
