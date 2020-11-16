FROM openjdk:8-jdk-alpine

VOLUME /a402

COPY backend/file-service/build/libs/*.jar file-service.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "file-service.jar"]
