package me.vitoremanoe.modulejar.repositoryTest;

import me.vitoremanoel.modulejar.ModuleRepository;
import me.vitoremanoel.modulejar.configuration.RepositoryMode;

public class RepositoryTest extends ModuleRepository {

    public static void main(String[] args){
        RepositoryTest repository = new RepositoryTest();
        repository.startRepository();
    }

    public RepositoryTest(){
        super.setRepositoryConfiguration($ -> {
            $.setMode(RepositoryMode.LOCAL);
            $.setRepository("./plugins");
        });
        super.initRepository();
    }
}
