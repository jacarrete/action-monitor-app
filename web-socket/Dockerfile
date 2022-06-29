FROM openjdk:11-jre-slim

LABEL maintainer="javi_1986@hotmail.com"

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} web-socket.jar
ENTRYPOINT ["java", "-jar", "web-socket.jar"]