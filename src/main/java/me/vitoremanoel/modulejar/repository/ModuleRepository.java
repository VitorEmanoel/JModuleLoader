package me.vitoremanoel.modulejar.repository;

import me.vitoremanoel.modulejar.configuration.JRepositoryConfiguration;
import me.vitoremanoel.modulejar.configuration.RepositoryMode;
import me.vitoremanoel.modulejar.dependecy.DependencyManager;
import me.vitoremanoel.modulejar.listeners.events.OnModuleStop;
import me.vitoremanoel.modulejar.loader.LocalRepositoryLoader;
import me.vitoremanoel.modulejar.loader.RemoteRepositoryLoader;
import me.vitoremanoel.modulejar.module.JModule;
import me.vitoremanoel.modulejar.loader.RepositoryLoader;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import java.util.function.Consumer;

public abstract class ModuleRepository {

    private JRepositoryConfiguration repositoryConfiguration;
    private RepositoryLoader loaderManager;
    private DependencyManager dependencyManager;
    private List<JModule> modules;
    public ModuleRepository(){
        this.dependencyManager = DependencyManager.getInstance();
    }

    public ModuleRepository setRepositoryConfiguration(Consumer<JRepositoryConfiguration> configuration) {
        JRepositoryConfiguration JRepositoryConfiguration = new JRepositoryConfiguration();
        configuration.accept(JRepositoryConfiguration);
        this.repositoryConfiguration = JRepositoryConfiguration;
        if (this.repositoryConfiguration.getMode() == RepositoryMode.LOCAL) {
            try {
                this.loaderManager = new LocalRepositoryLoader(new File(this.repositoryConfiguration.getPath()), this.getClass().getClassLoader());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        if (this.repositoryConfiguration.getMode() == RepositoryMode.REMOTE) {
            this.loaderManager = new RemoteRepositoryLoader(this.repositoryConfiguration.getRemoteFiles());
        }

        return this;
    }

    public ModuleRepository setRepositoryConfiguration(JRepositoryConfiguration JRepositoryConfiguration) {
        this.repositoryConfiguration = JRepositoryConfiguration;
        return this;
    }

    public DependencyManager getDependencyManager() {
        return this.dependencyManager;
    }

    private void setupFolders(){
        File modulesFolder = new File(this.repositoryConfiguration.getPath());
        if(!modulesFolder.exists()){
            System.out.println("[Warning] Modules folder not exists try creating");
            if(!modulesFolder.mkdir()){
                System.out.println("[Error] Could not create modules folder");
                return;
            }
            System.out.println("[Info] Modules folder created successfully");
        }
    }

    public void init(String[] args) {
        if (this.repositoryConfiguration.getMode() == RepositoryMode.LOCAL)
            setupFolders();
        this.modules = this.loaderManager.loadModules();
    }

    public void stop() {
        this.modules.forEach(module -> module.getListenerManager().call(new OnModuleStop()));
    }
}
