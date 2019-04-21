package me.vitoremanoel.modulejar;

import me.vitoremanoel.modulejar.configuration.RepositoryConfiguration;

import java.util.function.Consumer;

public interface IModuleRepository {

    void initRepository();
    void startRepository();
    void stopRepository();
    IModuleRepository setRepositoryConfiguration(Consumer<RepositoryConfiguration> configuration);
    IModuleRepository setRepositoryConfiguration(RepositoryConfiguration repositoryConfiguration);

}
