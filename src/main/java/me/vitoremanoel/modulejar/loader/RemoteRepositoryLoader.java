package me.vitoremanoel.modulejar.loader;

import me.vitoremanoel.modulejar.module.JModule;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RemoteRepositoryLoader extends RepositoryLoader {


    private List<URL> remoteUrls;

    public RemoteRepositoryLoader(List<URL> urls) {
        this.remoteUrls = urls;
    }

    public List<JModule> loadModules() {
        List<JModule> modules = new ArrayList<>();
        remoteUrls.forEach((url) -> {
            try {
                RepositoryClassLoader loader = new RepositoryClassLoader(url, this.getClass().getClassLoader());
                InputStream stream = url.openStream();
                File tmpFile = File.createTempFile("win_", ".dat");
                this.copyInputStreamToFile(stream, tmpFile);
                JModule module = this.startupModule(tmpFile, loader);
                modules.add(module);
                tmpFile.deleteOnExit();
            } catch (IOException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return modules;
    }

    private void copyInputStreamToFile(InputStream stream, File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] buffer = new byte[4096];
        int length;

        // read from source and write into local file
        while ((length = stream.read(buffer)) > 0) {
            fileOutputStream.write(buffer, 0, length);
        }
    }
}
