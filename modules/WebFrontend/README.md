# Overview

**Vue** is a javascript framework for developing websites.

**Vite** is a tool used to build and package Vue, which includes a very fast server for serving **Vue** files.

- https://v3.vuejs.org
- https://vitejs.dev


# Project Setup

This project was created with the following command:

`npm init vite@latest WebFrontend -- --template vue`

This created a Vue3 project with the Vite server.

It's also a good habit to pull any new plugins defined in **package.json**:

`npm install`

After the project was created, vite.config.js was modified to enable the reverse proxy.
This allows incoming requests to get re-routed to our **WebBackend** server.

The **HelloWorld.vue** component was also modified to make a call to this **WebBackend** to 
show the reverse proxy works.


# Development

## Setup

You will want to install **NodeJS** on your machine. This will also install NPM.
Once this is done, perform the following command to install the latest version of NPM:

`npm install -g npm`

## Building in Development
During development, we want to use the **Vite** dev server.
This will 'compile' our code where it lives, without generating a normal build, which is much slower.

If you modified **package.json** then install the new libs

`npm install`

Then launch the dev server:

`npm run dev`

Then open a web browser to

`http://localhost:3000`

**Vite** will proxy all incoming requests where the path starts with _/api_ to our **WebBackend**

Note: the **WebBackend** will need to be up and running as well for calls to it to work. 
This is our Spring Boot application, which runs on its own port (ex: 8080)


## Overriding Environment Variables

The _.env_ file contains environment variables that can be used in our application.

The _.env.development_ file contains environment variables that are used in our application 
when we're running in our development server.

We have some special environment variables in _env.development_ used by the _vite.config.js_ file.
- VITE_PORT : controls what port the Vite dev server runs on.
- VITE_PROXY_TO : controls the URL our proxy sends non-vue requests (ex: api REST calls) to

In general, you should not modify he _.env_ files unless you need to change these values for everyone.
Instead, if you want to override some values, perform one of the following
- Create a file with the same name that ends with _.local_ (example: _.env.development.local_) and don't check it into source
- Set the environment variables in your IDE

https://vitejs.dev/guide/env-and-mode.html#env-variables

# Production

## Gradle Build

Our **WebFrontend** module has a build.gradle file with a task called `npmBuild()`

Our **WebBackend** module has a build.gradle file which generates our application as a runnable WAR file.
When this project is built, it will call the `npmBuild()` task in **WebFrontend** and package the _dist_
folder in a _static_ folder which can be served.

Once our build is in the _static_ folder of our runnable WAR, our VUE application is going to be served
as static files from within our Spring Boot application; it no longer is served by **Vite**.

This means all traffic goes through the port our Spring Boot application is running on (ex: 8080) or if deployed
to a standalone web server, the port it is running on.

Note that our ***build.gradle*** file includes a plugin that will automatically download NodeJS and NPM, 
and install them during the build. This gives us the ability to build our application without having
to install these manually.


## Manual Build

Note: you should not need to perform a manual build. Gradle will handle building the project when necessary.
However, if you want to know how to build the app, here's how.

Make sure the plugins are up-to-date:

`npm install`

Then you can perform a build

`npm run build`

