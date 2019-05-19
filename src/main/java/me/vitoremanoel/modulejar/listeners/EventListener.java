package me.vitoremanoel.modulejar.listeners;

public interface EventListener<T extends Event> {

    void handle(T event);

}
