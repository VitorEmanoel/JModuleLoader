package me.vitoremanoe.modulejar.repositoryTest;

import me.vitoremanoel.modulejar.repository.ModuleRepository;
import me.vitoremanoel.modulejar.configuration.RepositoryMode;

public class RepositoryTest extends ModuleRepository {

    public static void main(String[] args){
        RepositoryTest repository = new RepositoryTest();
        repository.init(args);
    }

    public RepositoryTest(){
        super.setRepositoryConfiguration($ -> {
            $.setMode(RepositoryMode.LOCAL);
            $.setRepository("plugins");
        });
        super.getDependencyManager().registerDependency(RepositoryTest.class, this);
    }
}
