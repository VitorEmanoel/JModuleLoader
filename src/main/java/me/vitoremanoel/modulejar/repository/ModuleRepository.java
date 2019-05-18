package me.vitoremanoel.modulejar.repository;

import me.vitoremanoel.modulejar.configuration.RepositoryConfiguration;
import me.vitoremanoel.modulejar.dependecy.DependencyManager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class ModuleRepository implements IModuleRepository {

    private RepositoryConfiguration repositoryConfiguration;
    private DependencyManager dependencyManager;
    public ModuleRepository(){
        this.dependencyManager = new DependencyManager();
    }

    @Override
    public IModuleRepository setRepositoryConfiguration(Consumer<RepositoryConfiguration> configuration) {
        RepositoryConfiguration repositoryConfiguration = new RepositoryConfiguration();
        configuration.accept(repositoryConfiguration);
        this.repositoryConfiguration = repositoryConfiguration;
        return this;
    }

    @Override
    public IModuleRepository setRepositoryConfiguration(RepositoryConfiguration repositoryConfiguration) {
        this.repositoryConfiguration = repositoryConfiguration;
        return this;
    }

    @Override
    public DependencyManager getDependencyManager() {
        return this.dependencyManager;
    }

    @Override
    public List<File> getModulesFiles() {
        File moduleFolder = new File(this.repositoryConfiguration.getPath());
        if(moduleFolder.listFiles() != null)
            return Arrays.stream(moduleFolder.listFiles())
                    .filter($ -> $.getName().endsWith(".jar") && !$.isDirectory())
                    .collect(Collectors.toList());
        throw new NullPointerException("Module folder " + this.repositoryConfiguration.getPath() + " not found");
    }

    private void setupFolders(){
        File modulesFolder = new File(this.repositoryConfiguration.getPath());
        if(!modulesFolder.exists()){
            System.out.println("[Warning] Modules folder not exists try creating");
            if(!modulesFolder.mkdir()){
                System.out.println("[Error] Could not create modules folder");
                return;
            }
            System.out.println("[Info] Modules folder created successfully");
        }
    }
    private void loadJar(File jar) throws IOException {
        JarFile javaLoader = new JarFile(jar);
        javaLoader.stream().forEach(System.out::println);
    }

    private void loadJars(){
        this.getModulesFiles().forEach($ -> {
            try {
                this.loadJar($);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    @Override
    public void initRepository() {
        setupFolders();
        loadJars();
    }
}
