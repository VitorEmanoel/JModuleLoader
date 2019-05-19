package me.vitoremanoel.modulejar.configuration;

public class JRepositoryConfiguration implements RepositoryConfiguration {

    private RepositoryMode mode;
    private String path;

    public RepositoryConfiguration setMode(RepositoryMode mode) {
        this.mode = mode;
        return this;
    }

    public RepositoryConfiguration setRepository(String path) {
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
