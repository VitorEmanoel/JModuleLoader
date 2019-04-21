package me.vitoremanoel.modulejar.configuration;

public class RepositoryConfiguration implements IRepositoryConfiguration {

    private RepositoryMode mode;
    private String path;

    public IRepositoryConfiguration setMode(RepositoryMode mode) {
        this.mode = mode;
        return this;
    }

    public IRepositoryConfiguration setRepository(String path) {
        this.path = path;
        return this;
    }

    public String getPath(){
        return this.path;
    }

    public RepositoryMode getMode(){
        return this.mode;
    }
}
