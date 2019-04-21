package me.vitoremanoel.modulejar.configuration;


public interface IRepositoryConfiguration {

    IRepositoryConfiguration setMode(RepositoryMode mode);
    IRepositoryConfiguration setRepository(String path);

}
