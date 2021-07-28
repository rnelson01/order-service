# Pull base image
From tomcat:8-jre8

MAINTAINER "Anubhaw <anubhaw@wings.software"


COPY ./AppServerAgent-4.3.8.0.zip /usr/local/
RUN mkdir /usr/local/AppServerAgent-4.3.8.0
RUN unzip /usr/local/AppServerAgent-4.3.8.0.zip -d /usr/local/AppServerAgent-4.3.8.0

# Copy to images tomcat path
COPY target/todolist.war /usr/local/tomcat/webapps/todolist.war

COPY generate-load.sh .
CMD ./generate-load.sh
