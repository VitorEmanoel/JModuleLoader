package me.vitoremanoel.modulejar.listeners;

@FunctionalInterface
public interface EventListener<T extends Event> {

    void handle(T event);

}
