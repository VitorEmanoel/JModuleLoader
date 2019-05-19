package me.vitoremanoel.modulejar.loader;

import me.vitoremanoel.modulejar.annotations.Module;
import me.vitoremanoel.modulejar.listeners.events.OnModuleInit;
import me.vitoremanoel.modulejar.module.JModule;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class RepositoryLoaderManager {
    private File pluginFolder;

    public RepositoryLoaderManager(File pluginFolder, ClassLoader parent) throws MalformedURLException {
        this.pluginFolder = pluginFolder;
    }

    public List<File> getModulesFiles() {
        if(this.pluginFolder.listFiles() != null)
            return Arrays.stream(this.pluginFolder.listFiles())
                    .filter($ -> $.getName().endsWith(".jar") && !$.isDirectory())
                    .collect(Collectors.toList());
        throw new NullPointerException("JModule folder " + this.pluginFolder + " not found");
    }

    public List<JModule> loadModules() {
        List<JModule> modules = new ArrayList<>();

        for (File moduleFile : this.getModulesFiles()) {
            try{
                RepositoryClassLoader classLoader = new RepositoryClassLoader(moduleFile.toURI().toURL(), this.getClass().getClassLoader());
                Class<?> mainClass = this.findMainClass(this.getJarEntries(new JarFile(moduleFile)), classLoader);
                if(mainClass == null) continue;
                Module moduleAnnotation = mainClass.getAnnotation(Module.class);
                JModule module = new JModule(moduleAnnotation.name(), moduleAnnotation.version(), moduleAnnotation.description(), mainClass.newInstance());
                module.getListenerManager().call(new OnModuleInit());
                modules.add(module);
            }catch (IllegalAccessException e){
                e.printStackTrace(); // Construtor privado
            }catch (InstantiationException e){
                e.printStackTrace();
            }catch(IOException ex) {
                ex.printStackTrace(); // IO Error
            }
        }
        return modules;
    }

    private Class<?> findMainClass(List<JarEntry> jarEntries, ClassLoader classLoader){
        try {
            for (JarEntry jarEntry : jarEntries) {
                String className = jarEntry.toString().replaceAll("/", ".").replaceAll(".class", "");
                Class<?> jarEntryClass = Class.forName(className, true, classLoader);
                if (!jarEntryClass.isAnnotationPresent(me.vitoremanoel.modulejar.annotations.Module.class)) continue;
                return jarEntryClass;
            }
        }catch(ClassNotFoundException e){
            e.printStackTrace(); // Classe não encontrada no ClassLoader
        }
        return null;
    }

    private List<JarEntry> getJarEntries(JarFile jarFile){
        List<JarEntry> jarEntries = new ArrayList<>();
        Enumeration<JarEntry> jarFileEntries = jarFile.entries();
        while(jarFileEntries.hasMoreElements()){
            JarEntry jarFileEntry = jarFileEntries.nextElement();
            if(jarFileEntry.toString().endsWith(".class")){
                jarEntries.add(jarFileEntry);
            }
        }
        return jarEntries;
    }
}
