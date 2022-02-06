# Overview

This gradle module contains the main Spring Boot application which we want to build.

It has references to the **BackendService** module as a dependency, 
and the **WebFrontend** module when it needs to package its contents in our runnable war/jar.


# Development

## Preferred method

The preferred method is to have your IDE launch the **WebBackend** spring boot app,
and then to manually launch the **WebFrontend** vite dev server.

There are launcher files for IntelliJ in the *.idea* folder.

When you launch the app this way, you want to connect to the vite dev server 
on http://localhost:3000, which will proxy any request that starts with 
*/api* to the spring boot app.


## Simple method

If you want, you can also use *bootRun* to launch the application.
This will compile the frontend code from **WebFrontend** and package it into our app.

You can do this will the following command

`gradlew bootRun`

You can now open your web browser to http://localhost:8080, and everything will be 
served by Spring Boot, including your frontend code

However, since this causes a full build of the **WebFrontend** any changes you make
to the VueJS code won't show up unless you either restart the *bootRun* task,
or run the *copyWebFrontendDistToBuild* task

`gradlew :modules:WebBackend:copyWebFrontendDistToBuild`


# Production Build

## WAR

You just need to call the *build* task.
This will generate a runnable WAR file that can be deployed to a web server, like Apache Tomcat.

It can also be run stand-alone like a runnable jar

`java -jar SpringGradleVueViteExample-x.x.x.war`

## JAR

If you want a runnable JAR you will need to call the *bootJar* task.

This is imilar to the runnable WAR, except it cannot be deployed to a web server

`java -jar SpringGradleVueViteExample-x.x.x.jar`
