package me.vitoremanoel.modulejar.moduleTest;

import me.vitoremanoel.modulejar.annotations.Inject;
import me.vitoremanoel.modulejar.annotations.Listener;
import me.vitoremanoel.modulejar.annotations.Module;
import me.vitoremanoel.modulejar.listeners.events.OnModuleInit;

import java.util.logging.Logger;

@Module(name = "ModuloTeste", version = "1.0")
public class ModuleTest {

    @Inject
    private Logger logger;

    @Listener
    public void event(OnModuleInit e) {

    }

}
