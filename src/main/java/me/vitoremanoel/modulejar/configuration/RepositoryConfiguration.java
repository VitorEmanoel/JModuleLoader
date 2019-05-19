package me.vitoremanoel.modulejar.configuration;


public interface RepositoryConfiguration {

    RepositoryConfiguration setMode(RepositoryMode mode);
    RepositoryConfiguration setRepository(String path);

}
