# Setting up Lombok with IntelliJ and Eclipse
## 1. Lombok in IntelliJ IDEA
#### 1.1. Enabling Annotation Processing
Lombok uses annotation processing through APT, so, when the compiler calls it, the library generates new source files based on annotations in the originals.

**Annotation processing isn't enabled by default, though.**

So, the first thing for us to do is to enable annotation processing in our project.

We need to go to the *Preferences | Build, Execution, Deployment | Compiler | Annotation Processors* and make sure of the following:

* Enable annotation processing box is checked.
* Obtain processors from project classpath option is selected.

#### 1.2. Installing the IDE Plugin
As Lombok generates code only during compilation, the IDE highlights errors in raw source code:

There is a dedicated plugin which makes IntelliJ aware of the source code to be generated. **After installing it, the errors go away and regular features like ***Find Usages, Navigate To*** start working.**

We need to go to the *Preferences | Plugins*, open the *Marketplace* tab, type *lombok* and choose *Lombok Plugin by Michail Plushnikov*:

Next, click the *Install* button on the plugin page. After the installation, click the *Restart IDE* button.

## 2. Lombok in Eclipse
If we're using Eclipse IDE, we need to get the Lombok jar first. The latest version is located on Maven Central. In this project *lombok-1.18.16.jar* is being used.

Next, we can run the jar via *java -jar* command and an installer UI will open. This tries to automatically detect all available Eclipse installations, but it's also possible to specify the location manually.

Once we've selected the installations, then we press the *Install/Update* button.

If the installation is successful, we can exit the installer.

After installing the plugin, we need to restart the IDE and ensure that Lombok is correctly configured. We can check this in the *About* dialog.

## 3. Adding Lombok to the Compile Classpath
The last remaining part is to ensure that Lombok binaries are on the compiler classpath. Using Maven, we can add the dependency to the *pom.xml*:

    <dependencies>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.10</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>
    
The most recent version is located on *Maven Central*. You might need to *import dependencies* if you don't have *enable auto-import* turned on. 

Everything should be fine now, the source code below should be shown without errors in the IDE, correctly compiled and executed:

    public class UserIntegrationTest {
    
        @Test
        public void givenAnnotatedUser_thenHasGettersAndSetters() {
            User user = new User();
            user.setFirstName("Test");
            assertEquals(user.gerFirstName(), "Test");
        }
    
        @Getter @Setter
        class User {
            private String firstName;
        }
    }

## 4. Conclusion
Lombok does a great job in reducing Java verbosity and covering boilerplate stuff under the hood. In this article, we checked how to configure the tool for the two most popular Java IDEs.
