package me.vitoremanoel.modulejar.repository;

import me.vitoremanoel.modulejar.configuration.RepositoryConfiguration;
import me.vitoremanoel.modulejar.dependecy.DependencyManager;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public interface IModuleRepository {

    IModuleRepository setRepositoryConfiguration(Consumer<RepositoryConfiguration> configuration);
    IModuleRepository setRepositoryConfiguration(RepositoryConfiguration repositoryConfiguration);
    DependencyManager getDependencyManager();
    List<File> getModulesFiles();
    void initRepository();

}
