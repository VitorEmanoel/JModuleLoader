package me.vitoremanoel.modulejar.moduleTest;

import me.vitoremanoel.modulejar.listeners.EventListener;
import me.vitoremanoel.modulejar.listeners.events.OnModuleInit;


public class ListenerTest implements EventListener<OnModuleInit> {

    public int var = 0;

    @Override
    public void handle(OnModuleInit event) {
        var++;
    }

}
