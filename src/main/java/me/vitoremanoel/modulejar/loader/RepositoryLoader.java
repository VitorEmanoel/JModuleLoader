package me.vitoremanoel.modulejar.loader;

import me.vitoremanoel.modulejar.annotations.Module;
import me.vitoremanoel.modulejar.listeners.events.OnModuleInit;
import me.vitoremanoel.modulejar.module.JModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public abstract class RepositoryLoader {

    public abstract List<JModule> loadModules();

    protected Class<?> findMainClass(List<JarEntry> jarEntries, ClassLoader classLoader){
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

    protected JModule startupModule(File file, ClassLoader loader) throws IOException, IllegalAccessException, InstantiationException {
        Class<?> mainClass = this.findMainClass(this.getJarEntries(new JarFile(file)), loader);
        if(mainClass == null) return null;
        Module moduleAnnotation = mainClass.getAnnotation(Module.class);
        JModule module = new JModule(moduleAnnotation.name(), moduleAnnotation.version(), moduleAnnotation.description(), mainClass.newInstance());
        module.getListenerManager().call(new OnModuleInit());
        return module;
    }

    protected List<JarEntry> getJarEntries(JarFile jarFile){
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
