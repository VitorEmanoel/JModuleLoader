# JModuleLoader

JModuleLoader is library for you to create a repository of loadable modules with java

## Getting Started

### Prerequisites

 - Java 8+
 
### Installing

#### Maven

```
<dependency>
    <groupId>me.vitoremanoel</groupId>
    <artifactId>JModuleLoader</artifactId>
    <version>1.0</version>
</dependency>
```
```
<repository>
    <id>jcenter</id>
    <name>jcenter-bintray</name>
    <url>http://jcenter.bintray.com</url>
</repository>
```

#### Gradle

```
dependencies {
    compile 'me.vitoremanoel:JModuleLoader:1.0'
}

repositories {
    jcenter()
}
```


### Examples

#### Repository
Create you first repository
```


public class Repository extends ModuleRepository{
    
    public Repository(){
        this.setRepositoryConfiguration(config -> {
            config.setRepository("./modules");
            config.setMode(RepositoryMode.LOCAL);
        }
        
        public void init(String[] args){
            super.init(args);
        }
    }
}

public class Main{

    public static main(String[] args){
        Repository repository = new Repository();
        repository.init(args);
    }
}

```

#### Module
Create you first module

```
@Module(name="MyFirstModule", version="1.0", description="Only example module"
public class MyFirstModule{
  
    @Inject
    JModule module;
  
    @Listener
    public void event(OnModuleInit e){
        System.out.println("Hello world");
    }
  
}
```

### Documentation

#### Coming soon
