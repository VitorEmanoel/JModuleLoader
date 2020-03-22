package me.vitoremanoe.modulejar.repositoryTest;

import me.vitoremanoel.modulejar.configuration.RepositoryMode;
import me.vitoremanoel.modulejar.repository.ModuleRepository;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class RepositoryTest extends ModuleRepository {

    public static void main(String[] args){
        RepositoryTest repository = new RepositoryTest();
        repository.init(args);
        repository.stop();
    }

    public RepositoryTest(){
        ArrayList<URL> remoteFiles = new ArrayList<>();
        try {
            remoteFiles.add(new URL("http://localhost:8080/files/module-teste-1.0-SNAPSHOT.jar"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        super.setRepositoryConfiguration($ -> {
            $.setMode(RepositoryMode.REMOTE);
            $.setRemoteFiles(remoteFiles);
        });
        super.getDependencyManager().registerDependency(RepositoryTest.class, this);
    }
}
