package me.vitoremanoel.modulejar.repository;

import me.vitoremanoel.modulejar.configuration.JRepositoryConfiguration;
import me.vitoremanoel.modulejar.configuration.RepositoryConfiguration;
import me.vitoremanoel.modulejar.dependecy.DependencyManager;
import me.vitoremanoel.modulejar.loader.RepositoryLoaderManager;
import me.vitoremanoel.modulejar.module.JModule;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;

public abstract class ModuleRepository {

    private final DependencyManager dependencyManager;

    private RepositoryConfiguration repositoryConfiguration;
    private RepositoryLoaderManager loaderManager;
    private List<JModule> modules;

    public ModuleRepository() {
        this.dependencyManager = DependencyManager.getInstance();
    }

    public RepositoryConfiguration getRepositoryConfiguration() {
        return repositoryConfiguration;
    }

    public ModuleRepository setRepositoryConfiguration(Consumer<RepositoryConfiguration> configuration) {
        RepositoryConfiguration rc = new JRepositoryConfiguration();
        configuration.accept(rc);
        this.repositoryConfiguration = rc;
        return this;
    }

    public ModuleRepository setRepositoryConfiguration(RepositoryConfiguration repositoryConfiguration) {
        this.repositoryConfiguration = repositoryConfiguration;
        return this;
    }

    public DependencyManager getDependencyManager() {
        return this.dependencyManager;
    }

    private void setupFolders() {
        File modulesFolder = new File(this.repositoryConfiguration.getDirectory());
        if (!modulesFolder.exists()) {
            if (!modulesFolder.mkdir()) {
                System.out.println("[ERROR] Couldn't create modules folder.");
                return;
            }

            System.out.println("[INFO] Modules folder created successfully.");
        }
    }

    public void init() {
        setupFolders();

        this.loaderManager = new RepositoryLoaderManager(new File(this.repositoryConfiguration.getDirectory()));
        this.modules = this.loaderManager.loadModules();
    }
}
