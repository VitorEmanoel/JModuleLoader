package me.vitoremanoel.modulejar;

import me.vitoremanoel.modulejar.configuration.RepositoryConfiguration;

import java.util.function.Consumer;

public class ModuleRepository implements IModuleRepository {

    private RepositoryConfiguration repositoryConfiguration;

    public void initRepository() {

    }

    public void startRepository(){

    }

    public void stopRepository(){

    }

    public IModuleRepository setRepositoryConfiguration(Consumer<RepositoryConfiguration> configuration) {
        RepositoryConfiguration repositoryConfiguration = new RepositoryConfiguration();
        configuration.accept(repositoryConfiguration);
        this.repositoryConfiguration = repositoryConfiguration;
        return this;
    }

    public IModuleRepository setRepositoryConfiguration(RepositoryConfiguration repositoryConfiguration) {
        return this;
    }
}
