FROM openjdk:17-jdk-alpine
VOLUME /temporal
EXPOSE 8080
ARG JAR_FILE=target/rides-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]