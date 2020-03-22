package me.vitoremanoel.modulejar.loader;

import java.net.URL;
import java.net.URLClassLoader;

public class RepositoryClassLoader extends URLClassLoader {

    public RepositoryClassLoader(URL url, ClassLoader parent){
        super(new URL[] { url }, parent);
    }
}
