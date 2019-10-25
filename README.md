# Example how to use Vaadin Flow on Karaf

This Example also contains a Vaadin Flow Karaf feature which can be used as basis for experiments.

## Modules

* demo-app: A simple application that can be loaded
* feature-generator: Unused Module, only there to generate an initial feature.xml
* frontend: Contains an OSGi Capable Vaadin Servlet
* vaadin-feature: Contains the feature.xml

## Features

Currently there are 3 features available.

* vaadin-base: Contains the vaadin core without components
* vaadin-full: Contains the vaadin core and lots of vaadin components
* vaadin-servlet: starts the vaadin servlet (necessary to have a running application)

## Quickstart

Just add the feature repo with 
```
feature:repo-add mvn:org.pragmaticminds.plc4x/vaadin-feature/1.0.0-SNAPSHOT/xml/features
``` 
and install the `vaadin-servlet` feature with
```
feature:install vaadin-servlet
```
Then go to `localhost:8181` and you should see a Vaadin generated page.