package me.vitoremanoel.modulejar.configuration;

public interface RepositoryConfiguration {

    RepositoryMode getMode();

    RepositoryConfiguration setMode(RepositoryMode mode);

    String getDirectory();

    RepositoryConfiguration setDirectory(String path);

}
