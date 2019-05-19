package me.vitoremanoel.modulejar.repositoryTest;

import me.vitoremanoel.modulejar.configuration.RepositoryMode;
import me.vitoremanoel.modulejar.repository.ModuleRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RepositoryTest extends ModuleRepository {

    @BeforeAll
    public void setup() {
        setRepositoryConfiguration($ -> {
            $.setMode(RepositoryMode.LOCAL)
                    .setDirectory("modules");
        });

        getDependencyManager().registerDependency(RepositoryTest.class, this);
        init();
    }

    @Test
    public void testRepositoryDirectory() {
        assertTrue(new File(getRepositoryConfiguration().getDirectory()).exists());
    }

}
