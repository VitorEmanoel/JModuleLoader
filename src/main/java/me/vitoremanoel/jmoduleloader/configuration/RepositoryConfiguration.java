package me.vitoremanoel.jmoduleloader.configuration;


public interface RepositoryConfiguration {

    RepositoryConfiguration setMode(RepositoryMode mode);
    RepositoryConfiguration setRepository(String path);

}
