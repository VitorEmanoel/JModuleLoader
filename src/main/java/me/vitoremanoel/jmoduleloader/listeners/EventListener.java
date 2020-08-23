package me.vitoremanoel.jmoduleloader.listeners;

public interface EventListener<T extends Event> {

    void handle(T event);

}
