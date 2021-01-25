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