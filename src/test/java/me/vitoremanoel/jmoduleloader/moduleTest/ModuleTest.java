package me.vitoremanoel.jmoduleloader.moduleTest;

import me.vitoremanoel.jmoduleloader.annotations.Inject;
import me.vitoremanoel.jmoduleloader.annotations.Listener;
import me.vitoremanoel.jmoduleloader.annotations.Module;
import me.vitoremanoel.jmoduleloader.listeners.events.OnModuleInit;

import java.util.logging.Logger;

@Module(name = "ModuloTeste", version = "1.0")
public class ModuleTest {

    @Inject
    private Logger logger;

    @Listener
    public void event(OnModuleInit e){
        System.out.println("Hello world");
    }

}
