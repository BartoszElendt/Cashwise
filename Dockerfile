FROM openjdk:17-jdk-alpine
VOLUME /tmp
EXPOSE 8080
COPY target/budget-o-app-0.0.1-SNAPSHOT.jar budget-o-app.jar
ENTRYPOINT ["java","-jar","/budget-o-app.jar"]