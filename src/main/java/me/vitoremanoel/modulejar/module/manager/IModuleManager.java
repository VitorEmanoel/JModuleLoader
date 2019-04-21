package me.vitoremanoel.modulejar.module.manager;

import me.vitoremanoel.modulejar.module.Module;

public interface IModuleManager {

    Module getModule(String name);
    void stop(Module module);
    void start(Module module);
}
