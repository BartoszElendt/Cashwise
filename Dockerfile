FROM openjdk:17-jdk-alpine
VOLUME /tmp
EXPOSE 8080
COPY target/cashwise-0.0.1-SNAPSHOT.jar cashwise.jar
ENTRYPOINT ["java","-jar","/cashwise.jar"]