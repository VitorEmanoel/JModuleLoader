package me.vitoremanoel.modulejar.configuration;

import java.net.URL;
import java.util.List;

public class JRepositoryConfiguration implements RepositoryConfiguration {

    private RepositoryMode mode;
    private String path;
    private List<URL> remoteFiles;

    public RepositoryConfiguration setMode(RepositoryMode mode) {
        this.mode = mode;
        return this;
    }

    public RepositoryConfiguration setRepository(String path) {
        this.path = path;
        return this;
    }

    public List<URL> getRemoteFiles() {
        return remoteFiles;
    }

    public void setRemoteFiles(List<URL> remoteFiles) {
        this.remoteFiles = remoteFiles;
    }

    public String getPath(){
        return this.path;
    }

    public RepositoryMode getMode(){
        return this.mode;
    }
}
