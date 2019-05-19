package me.vitoremanoel.modulejar.configuration;

public class JRepositoryConfiguration implements RepositoryConfiguration {

    private RepositoryMode mode;
    private String directory;

    public RepositoryMode getMode() {
        return mode;
    }

    public String getDirectory() {
        return directory;
    }

    public RepositoryConfiguration setMode(RepositoryMode mode) {
        this.mode = mode;
        return this;
    }

    public RepositoryConfiguration setDirectory(String directory) {
        this.directory = directory;
        return this;
    }

}