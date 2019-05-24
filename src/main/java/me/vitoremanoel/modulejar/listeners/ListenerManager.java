package me.vitoremanoel.modulejar.listeners;

import me.vitoremanoel.modulejar.module.JModule;
import me.vitoremanoel.modulejar.module.ModuleEventHandler;

import java.lang.reflect.InvocationTargetException;

public class ListenerManager {

    private static ListenerManager instance;

    private ListenerManager() {
    }

    public static ListenerManager getInstance() {
        if (ListenerManager.instance == null)
            ListenerManager.instance = new ListenerManager();
        return ListenerManager.instance;
    }

    public void call(JModule module, Event event) throws IllegalAccessException, InvocationTargetException {
        for (ModuleEventHandler eventHandler : module.getListenerManager().getEventHandlers()) {

        }
    }
}
