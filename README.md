# Overview

This is a barebones example project that combines the following technologies into a monolithic application
with a SPA frontend framework:

- Gradle multi-module project
- Spring Boot 3
- Java 17
- VueJS framework
- ViteJS tooling

# Project Structure

The project is broken up into Gradle Modules which are located in the
***modules*** directory.
- **BackendService**: an example of a Spring Boot aware library
- **WebFrontend**: a VueJS application that uses Vite to serve files in development mode, and to compile the Vue app for production
- **WebBackend**: a Spring Boot application that references the other two projects

# Setup

You probably don't need to download anything in order for the application to build from gradle.

However, for development, it's preferable to download all the required apps separately. 
Here are the download links:
- **Java 17** https://adoptium.net/releases.html?variant=openjdk17&jvmVariant=hotspot
- **NodeJS** https://nodejs.org/en/download/

If you're using an IDE you may need to configure it to point to these locations

## IntelliJ

Simply open the root directory and it should find the build.gradle file, 
and setup most things for you.

Make sure your IDE has the proper tool locations.

First, make sure a Java 17 JDK is found:

First open `File > Project Structure > Project Settings > Project` and make sure the 
**SDK** option is set to your Java 17 JDK.
If it isn't, change it, click on the dropdown to see what Java JDK's the IDE knows about.
If you don't see a Java 17 option, then click the **Add SDK** option in the dropdown.
If you previously installed Java 17 JDK then select **JDK...** and go to the path you installed Java 17 JDK.
Otherwise, choose **Download JDK...** and select a JDK. I recommend the Eclipse Temurin (AdoptOpenJDK Hotspot) JDK.

Then go to `File > Settings > Build, Execution, Deployment > Build Tools > Gradle`. 
Make sure **Build and run using** and **Run tests using** are both set to *Gradle*
Then make sure **Use Gradle From** is set to *'gradle-wrapper.properties'* file
and that **Gradle JVM** is set to *Project SDK*


Lastly go to `File > Settings > Language & Frameworks > NodeJS` and make sure NodeJS and NPM locations are set
to the directories you installed them into. If they aren't, update the file paths.


# Production Build

Run the following command

`gradlew build`

This will generate a runnable WAR - and JAR if you prefer - file in the `/modules/WebBackend/build/libs` directory.


# Development

First start the Spring Boot service in **WebBackend**  - including the referenced **BackendService** library - 
by launching the `org.example.SpringGradleSpa.SpringGradleSpaApplication` class in your IDE. 
This will run a REST api that's accessible at http://localhost:8080

Then start the VueJS Vite server in **WebFrontend** run `npm run dev` and then open a web browser to 
http://localhost:3000. All frontend requests will be handled by Vite, and any request to the backend Spring Boot REST 
api will get routed to the Spring Boot app through the Vite proxy serer.

If you're using IntelliJ then there are launchers in the *.idea* folder that should
show up when you import the project that you can use to launch the application.

For more instructions on how to launch the **WebFrontend** and **WebBackend**
modules, please refer to the *README.md* file in their respective directories.
