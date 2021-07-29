# todolist

todolist is a simple app written in Java!


## Requirements

* Java 8 SDK
* Maven


## Building
1. run `mvn clean compile war:war` to generate the `target/todolist.war`
1. build and push regular Docker `wingsplugins/todolist:latest` image to https://hub.docker.com/r/wingsplugins/todolist/tags/
1. build and push `New Relic` Docker image `wingsplugins/todolist:newrelic`


## Dockerization
There are two `Dockerfiles`, one with `New Relic` built-in app and one without.
`NewRelic` version should only be used by developers interested in `New Relic`


##CIE pipeline 
go/harness360 

 
## Usage

    
### Demo
* [Modify sample source code](src/main/java/bookstore/Inventory.java)
* [Update README](README.md) 
