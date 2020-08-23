package me.vitoremanoel.jmoduleloader.loader;

import me.vitoremanoel.jmoduleloader.module.JModule;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LocalRepositoryLoader extends RepositoryLoader {

    private File pluginFolder;

    public LocalRepositoryLoader(File pluginFolder, ClassLoader parent) throws MalformedURLException {
        this.pluginFolder = pluginFolder;
    }

    private List<File> getModulesFiles() {
        if(this.pluginFolder.listFiles() != null)
            return Arrays.stream(Objects.requireNonNull(this.pluginFolder.listFiles()))
                    .filter($ -> $.getName().endsWith(".jar") && !$.isDirectory())
                    .collect(Collectors.toList());
        throw new NullPointerException("JModule folder " + this.pluginFolder + " not found");
    }

    public List<JModule> loadModules() {
        List<JModule> modules = new ArrayList<>();
        for (File moduleFile : this.getModulesFiles()) {
            try{
                RepositoryClassLoader classLoader = new RepositoryClassLoader(moduleFile.toURI().toURL(), this.getClass().getClassLoader());
                JModule module = this.startupModule(moduleFile, classLoader);
                modules.add(module);
            }catch (IOException | IllegalAccessException | InstantiationException e){
                e.printStackTrace(); // Construtor privado
            } // IO Error


        }
        return modules;
    }

}
