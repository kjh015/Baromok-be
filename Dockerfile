FROM amazoncorretto:21-alpine
EXPOSE 8080

COPY build/libs/test-spring-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]